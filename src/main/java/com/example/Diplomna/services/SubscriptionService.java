package com.example.Diplomna.services;

import com.example.Diplomna.GrabePicture.VideoMetadataRepr;
import com.example.Diplomna.GrabePicture.VideoWithUserInfo;
import com.example.Diplomna.classValid.CrmHelper;
import com.example.Diplomna.classValid.SubscriptionCrm;
import com.example.Diplomna.dto.*;
import com.example.Diplomna.enums.NotFoundException;
import com.example.Diplomna.enums.Role;
import com.example.Diplomna.model.*;
import com.example.Diplomna.repo.SubscriptionRepo;
import com.example.Diplomna.repo.UserRepo;
import com.example.Diplomna.repo.VideoRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SubscriptionService {

    @Autowired
    private final SubscriptionRepo subscriptionRepo;

    @Autowired
    private static UserRepo userRepo;

    @Autowired
    private static VideoRepo videoRepo;
    private final Logger logger = LoggerFactory.getLogger(SubscriptionService.class);
    @Autowired
    private UserService userService;
    @Autowired
    public SubscriptionService(SubscriptionRepo subscriptionRepo, UserRepo userRepo, VideoRepo videoRepo) {
        this.subscriptionRepo = subscriptionRepo;
        this.userRepo = userRepo;
        this.videoRepo = videoRepo;
    }
    public void toggleSubscription(String authorizationHeader, SubscriptionCrm subscriptionCrm) {
        CrmHelper crmHelper = new CrmHelper(userRepo);
        Long userId = crmHelper.userId(authorizationHeader);

        Subscription existsSubscription = subscriptionRepo.findByUserIdAndTargetUserId(userId,subscriptionCrm.getTarget_user_id());
        if (existsSubscription == null) {
            Subscription subscription = new Subscription();
            subscription.setUser(userId);
            subscription.setUser_target(subscriptionCrm.getTarget_user_id());
            subscription.setDateTime(LocalDateTime.now());
            subscription.setUnsubscribed(false);
            subscriptionRepo.save(subscription);
        } else if (existsSubscription.isUnsubscribed()) {
            existsSubscription.setUnsubscribed(false);
            subscriptionRepo.save(existsSubscription);
        } else {
            existsSubscription.setUnsubscribed(true);
            subscriptionRepo.save(existsSubscription);
        }
    }
    private static VideoWithUserInfo convert(Video video, User user) {
        VideoWithUserInfo repr = new VideoWithUserInfo();
        repr.setVideoId(video.getId());
        repr.setTitle(video.getTitle());
        repr.setPath(video.getPath());//
        repr.setDescription(video.getDescription());
        repr.setContentType(video.getVideoCategory().toString());
        repr.setAccessStatus(video.getAccessStatus().toString());
        repr.setUserName(user.getUserName());
        repr.setLink_video(video.getPath());
        try {
            byte[] avatarBytes = downloadAvaUser(user.getId());
            repr.setAvatarBytes(avatarBytes);
        } catch (IOException e) {
            // Обробити помилку завантаження аватарки
        }
        return repr;
    }
    public static byte[] downloadAvaUser(Long id) throws IOException {
        User user = userRepo.findById(id).orElseThrow(() -> new NotFoundException());
        return Files.readAllBytes(new File(user.getPhotoUrl()).toPath());
    }
    public static byte[] downloadVideo(String name) throws IOException {
        Video video = videoRepo.findByTitle(name).orElseThrow(() -> new NotFoundException());
        return Files.readAllBytes(new File(video.getPath()).toPath());
    }
    public List<SubscribersVideoDto> getVideoSubUser(String authorizationHeader) {
        CrmHelper crmHelper = new CrmHelper(userRepo);
        Long userId = crmHelper.userId(authorizationHeader);
        List<Long> subscribedUserIds = subscriptionRepo.findSubscribedUserIdsByUserId(userId);
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusMonths(1).withDayOfMonth(1);
        List<Video> subscribedVideos = videoRepo.findVideosByUserIdsAndDateRange(subscribedUserIds, startDate, endDate).stream()
                .filter(data->data.getAccessStatus() == 1)
                .toList();
        List<SubscribersVideoDto> result = new ArrayList<>();

        for (Video video : subscribedVideos) {
            Optional<User> user = userRepo.findById(video.getUser());

            result.add(SubscribersVideoDto.builder()
                    .id(video.getId())
                    .title(video.getTitle())
                    .uri(video.getPath())
                    .description(video.getDescription())
                    .views(video.getViews())
                    .user(UserDto.builder()
                            .id(user.get().getId())
                            .email(user.get().getEmail())
                            .displayName(user.get().getUserName())
                            .photoUrl(user.get().getPhotoUrl())
                            .build())
                    .build());
        }

        return result;
    }

    public long countSubscription(Long id) {
        return subscriptionRepo.countSubscribers(id);
    }

    public void unsubscribe(String authorizationHeader, Long targetUserId) {
        CrmHelper crmHelper = new CrmHelper(userRepo);
        Long userId = crmHelper.userId(authorizationHeader);
        Subscription subscription = subscriptionRepo.findByUserIdAndTargetUserId(userId, targetUserId);

        if (subscription != null) {
            subscriptionRepo.delete(subscription);
        }
    }


    public List<UserDto> getSubscribedUsers(@RequestHeader("Authorization") String authorizationHeader) {
        CrmHelper crmHelper = new CrmHelper(userRepo);
        Long userId = crmHelper.userId(authorizationHeader);
        logger.info("userId: " + userId);

        List<Subscription> subscriptions = subscriptionRepo.findAll().stream()
                .filter(data->data.getUser() == userId)
                .filter(data-> !data.isUnsubscribed())
                .toList();
        List<UserDto> result = new ArrayList<>();

        for (Subscription item: subscriptions) {
            Optional<User> user = userRepo.findById(item.getUser_target());
            result.add(UserDto.builder()
                            .id(user.get().getId())
                            .email(null)
                            .displayName(user.get().getUserName())
                            .photoUrl(user.get().getPhotoUrl())
                    .build());
        }

        return result;
    }




}

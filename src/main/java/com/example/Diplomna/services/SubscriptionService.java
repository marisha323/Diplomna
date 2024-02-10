package com.example.Diplomna.services;

import com.example.Diplomna.GrabePicture.VideoMetadataRepr;
import com.example.Diplomna.GrabePicture.VideoWithUserInfo;
import com.example.Diplomna.classValid.CrmHelper;
import com.example.Diplomna.classValid.SubscriptionCrm;
import com.example.Diplomna.enums.NotFoundException;
import com.example.Diplomna.enums.Role;
import com.example.Diplomna.model.Subscription;
import com.example.Diplomna.model.User;
import com.example.Diplomna.model.Video;
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
    public void addSubscription(String authorizationHeader, SubscriptionCrm subscriptionCrm) {
        CrmHelper crmHelper = new CrmHelper(userRepo);
        Long userId = crmHelper.userId(authorizationHeader);
        if (userId != null) {
            Subscription subscription = new Subscription();
            subscription.setUser(userId);
            subscription.setUser_target(subscriptionCrm.getTarget_user_id());
            subscription.setDateTime(LocalDateTime.now());
            subscription.setUnsubscribed(true);
            subscriptionRepo.save(subscription);
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
    public ResponseEntity<List<VideoWithUserInfo>> getVideoSubUser(@RequestHeader("Authorization") String authorizationHeader) {
        CrmHelper crmHelper = new CrmHelper(userRepo);
        Long userId = crmHelper.userId(authorizationHeader);
        List<Long> subscribedUserIds = subscriptionRepo.findSubscribedUserIdsByUserId(userId);
        logger.info("subscribedUserIds: " + subscribedUserIds);
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusMonths(1).withDayOfMonth(1);
        logger.info("startDate: " + startDate);
        logger.info("endDate: " + endDate);
        List<VideoWithUserInfo> subscribedVideos = videoRepo.findVideosByUserIdsAndDateRange(subscribedUserIds, startDate, endDate)
                .stream()
                .flatMap(video -> userService.findUserById(video.getUser())
                        .map(user -> Stream.of(convert(video,user)))
                        .orElseGet(Stream::empty))
                .collect(Collectors.toList());
        logger.info("subscribedVideos " + subscribedVideos);
        subscribedVideos.sort(Comparator.comparing(VideoWithUserInfo::getUploadDate, Comparator.nullsLast(Comparator.reverseOrder())));
        logger.info("subscribedVideos: " + subscribedVideos);
        return ResponseEntity.ok(subscribedVideos);
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


    public List<User> getSubscribedUsers(@RequestHeader("Authorization") String authorizationHeader) {
        CrmHelper crmHelper = new CrmHelper(userRepo);
        Long userId = crmHelper.userId(authorizationHeader);
        logger.info("userId: " + userId);

        List<Subscription> subscriptions = subscriptionRepo.findByUser_Id(userId);

        return subscriptions.stream()
                .map(subscription -> {
                    User user = new User(subscription.getUser_target());
                    if (user.getRole() == null) {
                        user.setRole(Role.USER);
                    }
                    return user;
                })
                .collect(Collectors.toList());
    }




}

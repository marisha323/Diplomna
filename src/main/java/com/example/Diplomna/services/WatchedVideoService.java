package com.example.Diplomna.services;

import com.example.Diplomna.Controller.VideoController;
import com.example.Diplomna.classValid.CrmHelper;
import com.example.Diplomna.classValid.Like_or_Dislike_Crm;
import com.example.Diplomna.classValid.VideoDTO;
import com.example.Diplomna.enums.NotFoundException;
import com.example.Diplomna.model.Subscription;
import com.example.Diplomna.model.User;
import com.example.Diplomna.model.Video;
import com.example.Diplomna.model.WatchedVideo;
import com.example.Diplomna.repo.SubscriptionRepo;
import com.example.Diplomna.repo.UserRepo;
import com.example.Diplomna.repo.VideoRepo;
import com.example.Diplomna.repo.WatchedVideoRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class WatchedVideoService {

    @Autowired
    private final WatchedVideoRepo watchedVideoRepo;

    @Autowired
    private static UserRepo userRepo;

    @Autowired
    private VideoRepo videoRepo;

    private final SubscriptionRepo subscriptionRepo;

    private static final Logger logger = LoggerFactory.getLogger(VideoController.class);

    public WatchedVideoService(WatchedVideoRepo watchedVideoRepo, UserRepo userRepo, VideoRepo videoRepo, SubscriptionRepo subscriptionRepo) {
        this.watchedVideoRepo = watchedVideoRepo;
        this.userRepo = userRepo;
        this.videoRepo = videoRepo;
        this.subscriptionRepo = subscriptionRepo;
    }

    public String like(String authorizationHeader, Like_or_Dislike_Crm likeOrDislikeCrm) {

        return null;
    }

    public boolean existsById(Long id) {
        return watchedVideoRepo.existsById(id);
    }

    public long countGradeLikeForVideoId(Long videoId) {
        return watchedVideoRepo.countGradeLikeForVideoId(videoId);
    }

    public long countGradeDislikeForVideoId(Long videoId) {
        return watchedVideoRepo.countGradeDislikeForVideoId(videoId);
    }

    public long countwatchForVideoId(Long videoId) {
        return watchedVideoRepo.countwatchForVideoId(videoId);
    }

    public List<VideoDTO> getLikedVideosByUser2(String authorizationHeader, Long gradeId) {
        Long userId = getUserIdFromAuthorizationHeader(authorizationHeader);
        List<Long> videoIds = watchedVideoRepo.findVideoIdsByUserIdAndGradeId(userId, gradeId);

        List<Video> likedVideos = videoRepo.findAllById(videoIds);

        List<VideoDTO> videoDTOs = likedVideos.stream()
                .map(video -> {
                    Long userId2 = video.getUser();
                    User user = userRepo.findById(userId2).orElse(null);
                    if (user != null) {
                        String userName = user.getUserName();
                        String photoUrl = user.getPhotoUrl();
                        String title = video.getTitle();
                        byte[] avatarBytes = new byte[0];
                        try {
                            avatarBytes = downloadAvaUser(userId2);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        return new VideoDTO(video, userName, photoUrl, avatarBytes);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return videoDTOs;
    }

    public static byte[] downloadAvaUser(Long id) throws IOException {
        User user = userRepo.findById(id).orElseThrow(() -> new NotFoundException());
        return Files.readAllBytes(new File(user.getPhotoUrl()).toPath());
    }


    private Long getUserIdFromAuthorizationHeader(String authorizationHeader) {
        CrmHelper crmHelper = new CrmHelper(userRepo);
        return crmHelper.userId(authorizationHeader);
    }

    public boolean getIsLiked(String authorizationHeader, Long videoId, Long gradeId) {
        Long userId = getUserIdFromAuthorizationHeader(authorizationHeader);
        WatchedVideo video = watchedVideoRepo.findByVideoId(videoId).stream()
                .filter(data -> data.getUser() == userId)
                .filter(data -> data.getGrade() == gradeId)
                .findFirst().orElse(null);


        return video != null;
    }

    public boolean setLike(String authorizationHeader, Like_or_Dislike_Crm likeOrDislikeCrm) {
        try {
            Long userId = getUserIdFromAuthorizationHeader(authorizationHeader);

            if (!videoRepo.existsById(likeOrDislikeCrm.getVideo_id()) && userId != null) {
                return false;
            }

            WatchedVideo dislike = watchedVideoRepo.findByVideoId(likeOrDislikeCrm.getVideo_id()).stream()
                    .filter(data -> data.getUser() == userId)
                    .filter(data -> data.getGrade() == 2)
                    .findFirst().orElse(null);

            if (dislike != null) {
                dislike.setGrade(1L);
                watchedVideoRepo.save(dislike);
                return true;
            }

            WatchedVideo like = watchedVideoRepo.findByVideoId(likeOrDislikeCrm.getVideo_id()).stream()
                    .filter(data -> data.getUser() == userId)
                    .filter(data -> data.getGrade() == likeOrDislikeCrm.getGrade_id())
                    .findFirst().orElse(null);

            if (like != null) {
                watchedVideoRepo.delete(like);
                return true;
            }



            WatchedVideo watchedVideo = new WatchedVideo();
            watchedVideo.setUser(userId);
            watchedVideo.setVideo(likeOrDislikeCrm.getVideo_id());
            watchedVideo.setGrade(likeOrDislikeCrm.getGrade_id());
            watchedVideo.setGradeDate(LocalDateTime.now());
            watchedVideoRepo.save(watchedVideo);

            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean setDisLike(String authorizationHeader, Like_or_Dislike_Crm likeOrDislikeCrm) {
        try {
            Long userId = getUserIdFromAuthorizationHeader(authorizationHeader);

            if (!videoRepo.existsById(likeOrDislikeCrm.getVideo_id()) && userId != null) {
                return false;
            }

            WatchedVideo like = watchedVideoRepo.findByVideoId(likeOrDislikeCrm.getVideo_id()).stream()
                    .filter(data -> data.getUser() == userId)
                    .filter(data -> data.getGrade() == 1)
                    .findFirst().orElse(null);

            if (like != null) {
                like.setGrade(2L);
                watchedVideoRepo.save(like);
                return true;
            }

            WatchedVideo dislike = watchedVideoRepo.findByVideoId(likeOrDislikeCrm.getVideo_id()).stream()
                    .filter(data -> data.getUser() == userId)
                    .filter(data -> data.getGrade() == likeOrDislikeCrm.getGrade_id())
                    .findFirst().orElse(null);

            if (dislike != null) {
                watchedVideoRepo.delete(dislike);
                return true;
            }



            WatchedVideo watchedVideo = new WatchedVideo();
            watchedVideo.setUser(userId);
            watchedVideo.setVideo(likeOrDislikeCrm.getVideo_id());
            watchedVideo.setGrade(likeOrDislikeCrm.getGrade_id());
            watchedVideo.setGradeDate(LocalDateTime.now());
            watchedVideoRepo.save(watchedVideo);

            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean getIsSubscribed(String authorizationHeader, Long targetUserId) {
        Long userId = getUserIdFromAuthorizationHeader(authorizationHeader);

        Subscription subscription = subscriptionRepo.findByUserIdAndTargetUserId(userId, targetUserId);

        if (subscription == null) {
            return false;
        }

        return !subscription.isUnsubscribed();
    }
}



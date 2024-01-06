package com.example.Diplomna.Controller;

import com.example.Diplomna.classValid.CrmHelper;
import com.example.Diplomna.classValid.Like_or_Dislike_Crm;
import com.example.Diplomna.model.WatchedVideo;
import com.example.Diplomna.repo.UserRepo;
import com.example.Diplomna.repo.WatchedVideoRepo;
import com.example.Diplomna.services.VideoService;
import com.example.Diplomna.services.WatchedVideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/grade")
public class WatchedVideoController {
    @Autowired
    private WatchedVideoService watchedVideoService;
    @Autowired
    private VideoService videoService;
    private UserRepo userRepo;
    private WatchedVideoRepo watchedVideoRepo;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public WatchedVideoController(UserRepo userRepo, WatchedVideoRepo watchedVideoRepo) {
        this.userRepo = userRepo;
        this.watchedVideoRepo = watchedVideoRepo;
    }

    @GetMapping("/like")
    public String like(@RequestHeader("Authorization")String authorizationHeader, Like_or_Dislike_Crm likeOrDislikeCrm) {
        CrmHelper crmHelper = new CrmHelper(userRepo);
        Long userId = crmHelper.userId(authorizationHeader);
        logger.info("userId " + userId);
            logger.info("watchedVideoService " + videoService.existsById(likeOrDislikeCrm.getVideo_id()));
        if (videoService.existsById(likeOrDislikeCrm.getVideo_id()) && userId != null) {
            WatchedVideo watchedVideo = new WatchedVideo();
            watchedVideo.setUser(userId);
            watchedVideo.setVideo(likeOrDislikeCrm.getVideo_id());
            watchedVideo.setGrade(likeOrDislikeCrm.getGrade_id());
            watchedVideo.setGradeDate(LocalDateTime.now());
            watchedVideoRepo.save(watchedVideo);
            return "jjjjj";
        } else {
            return "ERROR";
        }
    }
    @GetMapping("/dislike")
    public String dislike(@RequestHeader("Authorization")String authorizationHeader, Like_or_Dislike_Crm likeOrDislikeCrm) {
        CrmHelper crmHelper = new CrmHelper(userRepo);
        Long userId = crmHelper.userId(authorizationHeader);
        logger.info("userId " + userId);
        logger.info("watchedVideoService " + videoService.existsById(likeOrDislikeCrm.getVideo_id()));
        if (videoService.existsById(likeOrDislikeCrm.getVideo_id()) && userId != null) {
            WatchedVideo watchedVideo = new WatchedVideo();
            watchedVideo.setUser(userId);
            watchedVideo.setVideo(likeOrDislikeCrm.getVideo_id());
            watchedVideo.setGrade(likeOrDislikeCrm.getGrade_id());
            watchedVideo.setGradeDate(LocalDateTime.now());
            watchedVideoRepo.save(watchedVideo);
            return "jjjjj";
        } else {
            return "ERROR";
        }
    }

    @GetMapping("/countlike")
    public long countGradeLikeForVideoId(@RequestParam Long videoId) {
        return watchedVideoService.countGradeLikeForVideoId(videoId);
    }
    @GetMapping("/countdislike")
    public long countGradeDislikeForVideoId(@RequestParam Long videoId) {
        return watchedVideoService.countGradeDislikeForVideoId(videoId);
    }

    @GetMapping("/count_watched")
    public long countWatchedVideoId(@RequestParam Long videoId) {
        return watchedVideoService.countwatchForVideoId(videoId);
    }

}

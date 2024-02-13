package com.example.Diplomna.Controller;

import com.example.Diplomna.classValid.CrmHelper;
import com.example.Diplomna.classValid.Like_or_Dislike_Crm;
import com.example.Diplomna.classValid.VideoDTO;
import com.example.Diplomna.dto.PlayListVideoDto;
import com.example.Diplomna.model.PlayListVideo;
import com.example.Diplomna.model.Video;
import com.example.Diplomna.model.WatchedVideo;
import com.example.Diplomna.repo.UserRepo;
import com.example.Diplomna.repo.WatchedVideoRepo;
import com.example.Diplomna.services.VideoService;
import com.example.Diplomna.services.WatchedVideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private WatchedVideoRepo watchedVideoRepo;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public WatchedVideoController(UserRepo userRepo, WatchedVideoRepo watchedVideoRepo) {
        this.userRepo = userRepo;
        this.watchedVideoRepo = watchedVideoRepo;
    }

    @PostMapping("/like")
    public ResponseEntity<Boolean> like(@RequestHeader("Authorization")String authorizationHeader,@RequestBody Like_or_Dislike_Crm likeOrDislikeCrm) {
        boolean result = watchedVideoService.setLike(authorizationHeader, likeOrDislikeCrm);

        return ResponseEntity.ok(result);
    }
    @PostMapping ("/dislike")
    public ResponseEntity<Boolean> dislike(@RequestHeader("Authorization")String authorizationHeader, @RequestBody Like_or_Dislike_Crm likeOrDislikeCrm) {
        boolean result = watchedVideoService.setDisLike(authorizationHeader, likeOrDislikeCrm);

        return ResponseEntity.ok(result);
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


    @GetMapping("/liked-videos")
    public ResponseEntity<List<PlayListVideoDto>> getLikedVideosByUser(@RequestHeader("Authorization") String authorizationHeader) {
        return ResponseEntity.ok(watchedVideoService.getLikedVideosByUser2(authorizationHeader));
    }

    @GetMapping("/liked")
    public ResponseEntity<Boolean> gwtIsLiked (@RequestHeader("Authorization") String authorizationHeader, Long videoId) {
        boolean status = watchedVideoService.getIsLiked(authorizationHeader, videoId, 1L);

        return ResponseEntity.ok(status);
    }

    @GetMapping("/disliked")
    public ResponseEntity<Boolean> getIsDisLiked (@RequestHeader("Authorization") String authorizationHeader, Long videoId) {
        boolean status = watchedVideoService.getIsLiked(authorizationHeader, videoId, 2L);

        return ResponseEntity.ok(status);
    }

    @GetMapping("/subscribed")
    public ResponseEntity<Boolean> getIsSubscribed(@RequestHeader("Authorization") String authorizationHeader, Long targetUserId) {
        boolean status = watchedVideoService.getIsSubscribed(authorizationHeader, targetUserId);

        return ResponseEntity.ok(status);
    }
}

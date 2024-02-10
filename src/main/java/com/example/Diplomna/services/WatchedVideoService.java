package com.example.Diplomna.services;

import com.example.Diplomna.Controller.VideoController;
import com.example.Diplomna.GrabePicture.NewVideoRepr;
import com.example.Diplomna.classValid.CrmHelper;
import com.example.Diplomna.classValid.Like_or_Dislike_Crm;
import com.example.Diplomna.classValid.VideoDTO;
import com.example.Diplomna.model.User;
import com.example.Diplomna.model.Video;
import com.example.Diplomna.model.WatchedVideo;
import com.example.Diplomna.repo.UserRepo;
import com.example.Diplomna.repo.VideoRepo;
import com.example.Diplomna.repo.WatchedVideoRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class WatchedVideoService {

    @Autowired
    private final WatchedVideoRepo watchedVideoRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private VideoRepo videoRepo;

    private static final Logger logger = LoggerFactory.getLogger(VideoController.class);
    public WatchedVideoService(WatchedVideoRepo watchedVideoRepo, UserRepo userRepo,VideoRepo videoRepo) {
        this.watchedVideoRepo = watchedVideoRepo;
        this.userRepo = userRepo;
        this.videoRepo=videoRepo;
    }

    public String like(String authorizationHeader, Like_or_Dislike_Crm likeOrDislikeCrm){

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
                        return new VideoDTO(video, userName, photoUrl);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return videoDTOs;
    }




    private Long getUserIdFromAuthorizationHeader(String authorizationHeader) {
        CrmHelper crmHelper = new CrmHelper(userRepo);
        return crmHelper.userId(authorizationHeader);
    }
}



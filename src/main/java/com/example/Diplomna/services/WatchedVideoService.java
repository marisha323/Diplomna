package com.example.Diplomna.services;

import com.example.Diplomna.classValid.CrmHelper;
import com.example.Diplomna.classValid.Like_or_Dislike_Crm;
import com.example.Diplomna.model.WatchedVideo;
import com.example.Diplomna.repo.UserRepo;
import com.example.Diplomna.repo.WatchedVideoRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchedVideoService {

    private final WatchedVideoRepo watchedVideoRepo;
    private UserRepo userRepo;
    public WatchedVideoService(WatchedVideoRepo watchedVideoRepo, UserRepo userRepo) {
        this.watchedVideoRepo = watchedVideoRepo;
        this.userRepo = userRepo;
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
}

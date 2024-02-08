package com.example.Diplomna.services;

import com.example.Diplomna.classValid.CrmHelper;
import com.example.Diplomna.classValid.Like_or_Dislike_Crm;
import com.example.Diplomna.model.Video;
import com.example.Diplomna.model.WatchedVideo;
import com.example.Diplomna.repo.UserRepo;
import com.example.Diplomna.repo.VideoRepo;
import com.example.Diplomna.repo.WatchedVideoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WatchedVideoService {

    @Autowired
    private final WatchedVideoRepo watchedVideoRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private VideoRepo videoRepo;
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

    public List<Video> getLikedVideosByUser(Long userId, Long gradeId) {

       List<WatchedVideo> watchedVideos = watchedVideoRepo.findByUser_IdAndGrade_Id(userId, gradeId);

        List<Video> likedVideos = new ArrayList<>();
        for (WatchedVideo watchedVideo : watchedVideos) {
            Long videoId = watchedVideo.getVideo(); // Отримуємо ідентифікатор відео
            if (videoId != null) {
                Video video = videoRepo.findById(videoId).orElse(null);
                if (video != null) {
                    likedVideos.add(video);
                }
            }
        }
        return likedVideos;
    }
}

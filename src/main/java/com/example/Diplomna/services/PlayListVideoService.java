package com.example.Diplomna.services;

import com.example.Diplomna.classValid.PlayListVideoCrm;
import com.example.Diplomna.model.PlayListVideo;
import com.example.Diplomna.repo.PlayListRepo;
import com.example.Diplomna.repo.PlayListVideoRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayListVideoService {

    private final PlayListVideoRepo playListVideoRepo;
    private final Logger logger = LoggerFactory.getLogger(PlayListService.class);

    @Autowired
    public PlayListVideoService(PlayListVideoRepo playListVideoRepo) {
        this.playListVideoRepo = playListVideoRepo;
    }

    @PersistenceContext
    private EntityManager entityManager;

//    public boolean isDuplicateEntry(Long playListId, Long videoId) {
//        Query query = entityManager.createQuery("SELECT p FROM PlayListVideo p WHERE p.playList.id = :playListId OR p.video.id = :videoId");
//        query.setParameter("playListId", playListId);
//        query.setParameter("videoId", videoId);
//
//        List<?> resultList = query.getResultList();
//        return !resultList.isEmpty();
//    }

    public boolean addVideoPlayList(PlayListVideoCrm playListVideoCrm) {
        Optional<PlayListVideo> existingEntry = playListVideoRepo.findAll().stream()
                .filter(data -> data.getVideo() == playListVideoCrm.getVideoId())
                .filter(data -> data.getPlayList() == playListVideoCrm.getPlayListId())
                .findFirst();

        if (existingEntry.isEmpty()) {
            PlayListVideo playListVideo = new PlayListVideo();
            playListVideo.setPlayList(playListVideoCrm.getPlayListId());
            playListVideo.setVideo(playListVideoCrm.getVideoId());
            playListVideoRepo.save(playListVideo);
        } else {
            playListVideoRepo.delete(existingEntry.get());
        }

        return true;
    }
}

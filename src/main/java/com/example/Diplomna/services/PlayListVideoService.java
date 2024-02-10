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

    public void addVideoPlayList(PlayListVideoCrm playListVideoCrm) {
        if(playListVideoCrm.getVideoId()!=null && playListVideoCrm.getPlayListId()!= null){
            logger.info("playListVideoCrm.getVideoId() " + playListVideoCrm.getVideoId());
            PlayListVideo existingEntry = playListVideoRepo.findEntry(playListVideoCrm.getPlayListId(), playListVideoCrm.getVideoId());

            logger.info("existingEntry " + existingEntry);

            if (existingEntry == null) {

                logger.info("playListVideoCrm.getPlayListId() " + playListVideoCrm.getPlayListId());
                PlayListVideo playListVideo = new PlayListVideo();
                playListVideo.setPlayList(playListVideoCrm.getPlayListId());
                playListVideo.setVideo(playListVideoCrm.getVideoId());
                playListVideoRepo.save(playListVideo);
                logger.info("playListVideoCrm.getVideoId() " + playListVideoCrm.getVideoId());
            } else {
                throw new IllegalArgumentException("Відео з такими id вже існує в плейлисті.");
            }
        } else {
            throw new IllegalArgumentException("Невірні параметри для додавання відео в плейлист");
        }
    }
}

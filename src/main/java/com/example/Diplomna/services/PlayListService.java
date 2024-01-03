package com.example.Diplomna.services;

import com.example.Diplomna.classValid.CrmHelper;
import com.example.Diplomna.classValid.PlaylistCrm;
import com.example.Diplomna.model.PlayList;
import com.example.Diplomna.repo.PlayListRepo;
import com.example.Diplomna.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
public class PlayListService {
    private final PlayListRepo playListRepo;
    private final Logger logger = LoggerFactory.getLogger(PlayListService.class);

    private UserRepo userRepo;


    @Autowired
    public PlayListService(PlayListRepo playListRepo, UserRepo userRepo) {
        this.playListRepo = playListRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    public void addPlayList(String authorizationHeader, PlaylistCrm playlistCrm) {
        CrmHelper crmHelper = new CrmHelper(userRepo);
        Long userId = crmHelper.userId(authorizationHeader);
        logger.info("userId " + userId);

        logger.info("TITLE:" + playlistCrm.getTitle());
        if (userId != null && playlistCrm.getTitle()!=null && playlistCrm.getAccessStatus()!=null) {

            PlayList playList = new PlayList();
            playList.setAccessStatus(playlistCrm.getAccessStatus());
            playList.setTitle(playlistCrm.getTitle());
            playList.setUser(userId);
            playListRepo.save(playList);

        } else{
            throw new IllegalArgumentException("Невірні параметри для створення плейлисту");
        }

    }

    @Transactional
    public List<PlayList> getPlayListsByUserId(String authorizationHeader) {
        CrmHelper crmHelper = new CrmHelper(userRepo);
        Long userId = crmHelper.userId(authorizationHeader);
        logger.info("userId " + userId);

        if (userId != null) {
            return playListRepo.findByUserId(userId);
        } else {
            throw new IllegalArgumentException("Невірний ідентифікатор користувача");
        }
    }
}

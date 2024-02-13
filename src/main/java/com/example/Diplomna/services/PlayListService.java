package com.example.Diplomna.services;

import com.example.Diplomna.classValid.CrmHelper;
import com.example.Diplomna.classValid.PlaylistCrm;
import com.example.Diplomna.dto.PlaylistDto;
import com.example.Diplomna.dto.PlaylistForVideoDto;
import com.example.Diplomna.model.PlayList;
import com.example.Diplomna.model.PlayListVideo;
import com.example.Diplomna.repo.PlayListRepo;
import com.example.Diplomna.repo.PlayListVideoRepo;
import com.example.Diplomna.repo.UserRepo;
import com.example.Diplomna.request.DeletePlaylistRequest;
import com.example.Diplomna.request.PlaylistRequest;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PlayListService {
    private final PlayListRepo playListRepo;
    private final PlayListVideoRepo playListVideoRepo;
    private final Logger logger = LoggerFactory.getLogger(PlayListService.class);

    private UserRepo userRepo;


    @Autowired
    public PlayListService(PlayListRepo playListRepo, PlayListVideoRepo playListVideoRepo, UserRepo userRepo) {
        this.playListRepo = playListRepo;
        this.playListVideoRepo = playListVideoRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    public void addPlayList(String authorizationHeader, PlaylistCrm playlistCrm) {
        CrmHelper crmHelper = new CrmHelper(userRepo);
        Long userId = crmHelper.userId(authorizationHeader);
        logger.info("userId " + userId);

        logger.info("TITLE:" + playlistCrm.getTitle());
        if (userId != null && playlistCrm.getTitle() != null && playlistCrm.getAccessStatus() != null) {

            PlayList playList = new PlayList();
            playList.setAccessStatus(playlistCrm.getAccessStatus());
            playList.setTitle(playlistCrm.getTitle());
            playList.setUser(userId);
            playListRepo.save(playList);

        } else {
            throw new IllegalArgumentException("Невірні параметри для створення плейлисту");
        }

    }

    @Transactional
    public List<PlaylistDto> getPlayListsByUserId(String authorizationHeader) {
        CrmHelper crmHelper = new CrmHelper(userRepo);
        Long userId = crmHelper.userId(authorizationHeader);

        List<PlayList> playLists = playListRepo.findByUserId(userId);
        List<PlaylistDto> result = new ArrayList<>();

        for (PlayList playList : playLists) {
            int count = (int) playListVideoRepo.findAll().stream()
                    .filter(data -> data.getPlayList() == playList.getId())
                    .count();

            result.add(PlaylistDto.builder()
                            .id(playList.getId())
                            .title(playList.getTitle())
                            .accessStatus(playList.getAccessStatus())
                            .videoCount(count)
                    .build());
        }

        if (userId != null) {
            return result;
        } else {
            throw new IllegalArgumentException("Невірний ідентифікатор користувача");
        }
    }

    public List<PlaylistForVideoDto> getPlaylistsForVideo(String authorizationHeader, Long videoId) {
        CrmHelper crmHelper = new CrmHelper(userRepo);
        Long userId = crmHelper.userId(authorizationHeader);

        List<PlayList> playLists = playListRepo.findByUserId(userId);
        List<PlaylistForVideoDto> result = new ArrayList<>();

        for (PlayList playList : playLists) {
            Optional<PlayListVideo> object = playListVideoRepo.findAll().stream()
                    .filter(data -> data.getPlayList() == playList.getId())
                    .filter(data -> data.getVideo() == videoId)
                    .findFirst();

            result.add(PlaylistForVideoDto.builder()
                    .id(playList.getId())
                    .title(playList.getTitle())
                    .accessStatus(playList.getAccessStatus())
                    .alreadyAdded(!object.isEmpty())
                    .build());
        }

        if (userId != null) {
            return result;
        } else {
            throw new IllegalArgumentException("Невірний ідентифікатор користувача");
        }
    }

    public boolean updatePlaylist(PlaylistRequest request) {
        Optional<PlayList> playList = playListRepo.findById(request.getId());

        if (playList.isEmpty()) {
            return false;
        }

        playList.get().setAccessStatus(request.getAccessStatus());
        playList.get().setTitle(request.getTitle());
        playListRepo.save(playList.get());

        return true;
    }

    public boolean deletePlaylist(DeletePlaylistRequest request) {
        Optional<PlayList> playList = playListRepo.findById(request.getId());

        if (playList.isEmpty()) {
            return false;
        }

        playListRepo.delete(playList.get());

        return true;

    }
}

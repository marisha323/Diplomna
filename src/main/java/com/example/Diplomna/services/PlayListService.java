package com.example.Diplomna.services;

import com.example.Diplomna.repo.PlayListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayListService {
    private final PlayListRepo playListRepo;


    @Autowired
    public PlayListService(PlayListRepo playListRepo) {
        this.playListRepo = playListRepo;
    }
}

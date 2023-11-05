package com.example.Diplomna.services;

import com.example.Diplomna.repo.PlayListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayListVideoService {

private final PlayListRepo playListRepo;
@Autowired
    public PlayListVideoService(PlayListRepo playListRepo) {
        this.playListRepo = playListRepo;
    }
}

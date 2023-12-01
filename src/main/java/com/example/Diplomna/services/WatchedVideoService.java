package com.example.Diplomna.services;

import com.example.Diplomna.repo.WatchedVideoRepo;
import org.springframework.stereotype.Service;

@Service
public class WatchedVideoService {

    private final WatchedVideoRepo watchedVideoRepo;

    public WatchedVideoService(WatchedVideoRepo watchedVideoRepo) {
        this.watchedVideoRepo = watchedVideoRepo;
    }
}

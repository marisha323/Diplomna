package com.example.Diplomna.services;

import com.example.Diplomna.repo.VideoCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoCategoryService {

    @Autowired
    private final VideoCategoryRepo videoCategoryRepo;

    public VideoCategoryService(VideoCategoryRepo videoCategoryRepo) {
        this.videoCategoryRepo = videoCategoryRepo;
    }
}

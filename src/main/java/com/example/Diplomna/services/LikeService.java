package com.example.Diplomna.services;

import com.example.Diplomna.repo.LikeRepo;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    private final LikeRepo likeRepo;

    public LikeService(LikeRepo likeRepo) {
        this.likeRepo = likeRepo;
    }
}

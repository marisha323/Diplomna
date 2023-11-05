package com.example.Diplomna.services;

import com.example.Diplomna.repo.DisLikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisLikeService {
    private final DisLikeRepo disLikeRepo;
    @Autowired
    public DisLikeService(DisLikeRepo disLikeRepo) {
        this.disLikeRepo = disLikeRepo;
    }



}

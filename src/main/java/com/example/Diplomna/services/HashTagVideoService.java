package com.example.Diplomna.services;
import com.example.Diplomna.repo.ChannelRepo;
import com.example.Diplomna.repo.HashtagVideoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class HashTagVideoService {
    private final HashtagVideoRepo hashtagVideoRepo;
    @Autowired
     public HashTagVideoService(HashtagVideoRepo hashtagVideoRepo) {
        this.hashtagVideoRepo = hashtagVideoRepo;
    }
}

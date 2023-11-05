package com.example.Diplomna.services;
import com.example.Diplomna.repo.HashtagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class HashtagService {
    private final HashtagRepo hashtagRepo;
@Autowired
    public HashtagService(HashtagRepo hashtagRepo) {
        this.hashtagRepo = hashtagRepo;
    }


}
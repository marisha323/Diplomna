package com.example.Diplomna.services;
import com.example.Diplomna.repo.ChannelRepo;
import com.example.Diplomna.repo.LikeNotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class LikeNotificationService {
    private final LikeNotificationRepo likeNotificationRepo;
    @Autowired
    public LikeNotificationService(LikeNotificationRepo likeNotificationRepo) {
        this.likeNotificationRepo = likeNotificationRepo;
    }
}

package com.example.Diplomna.services;
import com.example.Diplomna.repo.ChannelRepo;
import com.example.Diplomna.repo.DislikeNotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class DislikeNotificationService {
    private final DislikeNotificationRepo dislikeNotificationRepo;
    @Autowired
    public DislikeNotificationService(DislikeNotificationRepo dislikeNotificationRepo) {
        this.dislikeNotificationRepo = dislikeNotificationRepo;
    }
}
package com.example.Diplomna.services;
import com.example.Diplomna.repo.ChannelRepo;
import com.example.Diplomna.repo.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class NotificationService {
    private final NotificationRepo notificationRepo;
    @Autowired
    public NotificationService(NotificationRepo notificationRepo) {
        this.notificationRepo = notificationRepo;
    }
}
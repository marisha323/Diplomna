package com.example.Diplomna.services;
import com.example.Diplomna.repo.ChannelRepo;
import com.example.Diplomna.repo.SubscribeNotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SubscribeNotificationService {
    private final SubscribeNotificationRepo subscribeNotificationRepo;
    @Autowired
    public SubscribeNotificationService(SubscribeNotificationRepo subscribeNotificationRepo) {
        this.subscribeNotificationRepo = subscribeNotificationRepo;
    }
}
package com.example.Diplomna.services;
import com.example.Diplomna.repo.ChannelRepo;
import com.example.Diplomna.repo.UnsubscribeNotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UnsubscribeNotificationService {
    private final UnsubscribeNotificationRepo unsubscribeNotificationRepo;
    @Autowired
    public UnsubscribeNotificationService(UnsubscribeNotificationRepo unsubscribeNotificationRepo) {
        this.unsubscribeNotificationRepo = unsubscribeNotificationRepo;
    }
}
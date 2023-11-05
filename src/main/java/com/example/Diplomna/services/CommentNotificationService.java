package com.example.Diplomna.services;
import com.example.Diplomna.repo.ChannelRepo;
import com.example.Diplomna.repo.CommentNotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CommentNotificationService {
    private final CommentNotificationRepo commentNotificationRepo;
    @Autowired
   public CommentNotificationService(CommentNotificationRepo commentNotificationRepo) {
        this.commentNotificationRepo = commentNotificationRepo;
    }
}
package com.example.Diplomna.repo;

import com.example.Diplomna.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepo extends JpaRepository<Notification,Long> {
}

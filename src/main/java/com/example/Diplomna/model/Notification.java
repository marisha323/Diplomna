package com.example.Diplomna.model;

import jakarta.persistence.*;

import java.io.Serializable;


public class Notification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "targetUserId")
    private User user;

    private String notificationType;
    private Long sourceId;

    private boolean isNew;

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", user=" + user +
                ", notificationType='" + notificationType + '\'' +
                ", sourceId='" + sourceId + '\'' +
                ", isNew=" + isNew +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public Long getNotificationMessage() {
        return sourceId;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.sourceId = sourceId;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public Notification(Long id, User user, String notificationType, String notificationMessage, boolean isNew) {
        this.id = id;
        this.user = user;
        this.notificationType = notificationType;
        this.sourceId = sourceId;
        this.isNew = isNew;
    }
}

package com.example.Diplomna.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.lang.annotation.Target;

@Entity
public class Notification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "targetUserId")
    private User user;
    //@ManyToOne
//    @JoinColumn(name = "notificationTypeId")
//    private Notification notification;
//    private Long targetUserId;
//    private Long notificationTypeId;
    private boolean isNew;

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", user=" + user +
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

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public Notification(Long id, User user, boolean isNew) {
        this.id = id;
        this.user = user;
        this.isNew = isNew;
    }
}

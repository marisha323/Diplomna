package com.example.Diplomna.model;

import com.example.Diplomna.contrscts.INotification;
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
    @OneToMany
    @JoinColumn(name = "notificationType")
    private INotification notification;

    private boolean isNew;

    public Notification(Long id, User user, INotification notification, boolean isNew) {
        this.id = id;
        this.user = user;
        this.notification = notification;
        this.isNew = isNew;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", user=" + user +
                ", notification=" + notification +
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

    public INotification getNotification() {
        return notification;
    }

    public void setNotification(INotification notification) {
        this.notification = notification;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}

package com.example.Diplomna.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Subscription implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @ManyToOne
    @JoinColumn(name = "targetUserId")
    private User user_target ;

    private LocalDateTime dateTime;
    private boolean Unsubscribed;
    public boolean isUnsubscribed() {
        return Unsubscribed;
    }

    public void setUnsubscribed(boolean unsubscribed) {
        Unsubscribed = unsubscribed;
    }



    public Subscription() {

    }

    @Override
    public String toString() {
        return "Subscribe{" +
                "id=" + id +
                ", user=" + user +
                ", user_target=" + user_target +
                ", dateTime=" + dateTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser() {
        return user != null ? user.getId() : null;
    }

    public void setUser(Long userId) {
        if (userId != null) {
            User user = new User();
            user.setId(userId);
            this.user = user;
        } else {
            this.user = null;
        }
    }
    public User getUser_target() {
        return user_target != null ? user_target : null;
    }
    public void setUser_target(Long user_target) {
        if (user_target != null) {
            User user = new User();
            user.setId(user_target);
            this.user_target = user;
        } else {
            this.user_target = null;
        }
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Subscription(Long id, User user, User user_target, LocalDateTime dateTime) {
        this.id = id;
        this.user = user;
        this.user_target = user_target;
        this.dateTime = dateTime;
    }
}

package com.example.Diplomna.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Subscription implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @ManyToOne
    @JoinColumn(name = "targetUserId")
    private User user_target ;

    private LocalDateTime dateTime;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser_target() {
        return user_target;
    }

    public void setUser_target(User user_target) {
        this.user_target = user_target;
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

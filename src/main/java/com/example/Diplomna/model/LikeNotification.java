package com.example.Diplomna.model;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
public class LikeNotification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "likeId")
    private Like like;

    @Override
    public String toString() {
        return "LikeNotification{" +
                "id=" + id +
                ", like=" + like +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Like getLike() {
        return like;
    }

    public void setLike(Like like) {
        this.like = like;
    }

    public LikeNotification(Long id, Like like) {
        this.id = id;
        this.like = like;
    }
}

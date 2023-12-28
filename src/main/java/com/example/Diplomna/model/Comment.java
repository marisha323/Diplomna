package com.example.Diplomna.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @ManyToOne
    @JoinColumn(name = "videoId")
    private Video video;
    private String text;
    private LocalDateTime dateTime;

    public Comment() {

    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user=" + user +
                ", video=" + video +
                ", text='" + text + '\'' +
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

    public Long getVideo() {
        return video != null ? video.getId() : null;
    }

    public void setVideo(Long videoId) {
        if (videoId != null) {
            Video video = new Video();
            video.setId(videoId);
            this.video = video;
        } else {
            this.video = null;
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Comment(Long id, User user, Video video, String text, LocalDateTime dateTime) {
        this.id = id;
        this.user = user;
        this.video = video;
        this.text = text;
        this.dateTime = dateTime;
    }


}





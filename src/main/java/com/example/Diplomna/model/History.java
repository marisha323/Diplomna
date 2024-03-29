package com.example.Diplomna.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class History implements Serializable {
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
    private LocalDateTime dateTime;

    public History() {
    }

    public History(Long id, User user, Video video, LocalDateTime dateTime) {
        this.id = id;
        this.user = user;
        this.video = video;
        this.dateTime = dateTime;
    }
    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", user=" + user +
                ", video=" + video +
                ", dateTime=" + dateTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }


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
//    public Video getVideo() {
//        return video;
//    }
//
//    public void setVideo(Video video) {
//        this.video = video;
//    }


    public Long getVideo() {
        return video != null ? video.getId() : null;}


    public void setVideo(Long video_id) {
        if (video_id != null) {
            Video video = new Video();
            video.setId(video_id);
            this.video = video;
        } else {
            this.video = null;
        }
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }


}

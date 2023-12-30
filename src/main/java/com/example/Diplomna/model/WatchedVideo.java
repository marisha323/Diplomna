package com.example.Diplomna.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class WatchedVideo implements Serializable {
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

    private int watchCount;
    private LocalDateTime watchDate;
    @ManyToOne
    @JoinColumn(name = "gradeId")
    private Grade grade;

    private LocalDateTime gradeDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser() {
        return user != null ? user.getId() : null;}

//    public void setUser(User user) {
//        this.user = user;
//    }
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
    public int getWatchCount() {
        return watchCount;
    }

    public void setWatchCount(int watchCount) {
        this.watchCount = watchCount;
    }

    public LocalDateTime getWatchDate() {
        return watchDate;
    }

    public void setWatchDate(LocalDateTime watchDate) {
        this.watchDate = watchDate;
    }


    public Long getGrade() {
        return grade != null ? grade.getId() : null;}


    public void setGrade(Long grade_id) {
        if (grade_id != null) {
            Grade grade = new Grade();
            grade.setId(grade_id);
            this.grade = grade;
        } else {
            this.grade = null;
        }
    }

    public LocalDateTime getGradeDate() {
        return gradeDate;
    }

    public void setGradeDate(LocalDateTime gradeDate) {
        this.gradeDate = gradeDate;
    }
}

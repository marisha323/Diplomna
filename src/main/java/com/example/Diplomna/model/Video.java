package com.example.Diplomna.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;

@Entity
public class Video implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    private String title;
    private String description;
    private String path;
    @ManyToOne
    @JoinColumn(name = "ownerId")
    private User ownerId;
    @ManyToOne
    @JoinColumn(name = "accessStatusId")
    private AccessStatus accessStatus;
//?????????
    //    @ManyToOne
//    @JoinColumn(name = "previewId")
//    private User user;
//    private Long previewId;
    private Long views;

    @ManyToOne
    @JoinColumn(name = "videoCategoryId")
    private VideoCategory videoCategory;

    private LocalDateTime uploadDate;
    private Time time;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getUser() {
        return ownerId != null ? ownerId.getId() : null;
    }

    public void setUser(Long userId) {
        if (userId != null) {
            User user = new User();
            user.setId(userId);
            this.ownerId = user;
        } else {
            this.ownerId = null;
        }

        public Long getAccessStatus() {
            return accessStatus != null ? accessStatus.getId() : null;
        }

        public void setAccessStatus(Long accessStatusId) {
            if (accessStatusId != null) {
                AccessStatus status = new AccessStatus();
                status.setId(accessStatusId);
                this.accessStatus = status;
            } else {
                this.accessStatus = null;
            }
        }
    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Long getVideoCategory() {
        return videoCategory != null ? videoCategory.getId() : null;
    }

    public void setVideoCategory(Long videoCategoryId) {
        if (videoCategoryId != null) {
            VideoCategory category = new VideoCategory();
            category.setId(videoCategoryId);
            this.videoCategory = category;
        } else {
            this.videoCategory = null;
        }
    }
    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }



    public Video() {
    }

    public Video(String title, String description, String path, User user, AccessStatus accessStatus, Long views, VideoCategory videoCategory, LocalDateTime uploadDate, Time time) {
        this.title = title;
        this.description = description;
        this.path = path;
        this.ownerId = user;
        this.accessStatus = accessStatus;
        this.views = views;
        this.videoCategory = videoCategory;
        this.uploadDate = uploadDate;
        this.time = time;
    }
}

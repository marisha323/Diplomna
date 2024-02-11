package com.example.Diplomna.classValid;

import com.example.Diplomna.model.Video;

public class VideoDTO {
    private Long id;
    private String title;
    private String description;
    private String path;
    private String userName;
    private String photoUrl;

    public byte[] getPhotoBete() {
        return photoBete;
    }

    public void setPhotoBete(byte[] photoBete) {
        this.photoBete = photoBete;
    }

    private byte[] photoBete;

    public VideoDTO(Video video, String userName, String photoUrl, byte[] photoBete) {

        this.id = video.getId();
        this.title = video.getTitle();
        this.description = video.getDescription();
        this.path = video.getPath();
        this.userName = userName;
        this.photoUrl = photoUrl;
        this.photoBete=photoBete;
    }

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}

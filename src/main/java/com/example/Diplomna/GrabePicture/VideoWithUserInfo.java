package com.example.Diplomna.GrabePicture;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class VideoWithUserInfo {
    private Long videoId;
    private String title;
    private String description;
    private String path;
    private LocalDateTime uploadDate;
    private String contentType;
    private String accessStatus;
    private String userName;
    private String link_video;
    private byte[] avatarBytes;

    public String getLink_video() {
        return link_video;
    }

    public void setLink_video(String link_video) {
        this.link_video = link_video;
    }

    public byte[] getAvatarBytes() {
        return avatarBytes;
    }

    public void setAvatarBytes(byte[] avatarBytes) {
        this.avatarBytes = avatarBytes;
    }


    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getAccessStatus() {
        return accessStatus;
    }

    public void setAccessStatus(String accessStatus) {
        this.accessStatus = accessStatus;
    }



    public VideoWithUserInfo() {

    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
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


    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public VideoWithUserInfo(Long videoId, String title, String description, String path, LocalDateTime uploadDate, String userName) {
        this.videoId = videoId;
        this.title = title;
        this.description = description;
        this.path = path;
        this.uploadDate = uploadDate;
        this.userName = userName;
    }
}

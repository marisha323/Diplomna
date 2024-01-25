package com.example.Diplomna.GrabePicture;


import com.example.Diplomna.model.AccessStatus;

//клас викор. для передачі інфи на фронт
public class VideoMetadataRepr {
    private Long id;

    private String title;
    private String description;

    private String contentType;
    private String path;
    private Long userId;
    private String username;

    public String getPathAVA() {
        return pathAVA;
    }

    public void setPathAVA(String pathAVA) {
        this.pathAVA = pathAVA;
    }

    private String pathAVA;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public String getAccessStatus() {
        return accessStatus;
    }

    public void setAccessStatus(String accessStatus) {
        this.accessStatus = accessStatus;
    }

    private String accessStatus;
    private String avatarPath;

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }



    public VideoMetadataRepr() {
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

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

package com.example.Diplomna.GrabePicture;

import org.springframework.web.multipart.MultipartFile;

//считати дані із форми
public class NewVideoRepr {

    private String description;
    private String title;

    private MultipartFile file;
    private Long categoryId;
    private Long accessStatusId;



    public NewVideoRepr() {
    }

    public Long getAccessStatusId() {
        return accessStatusId;
    }

    public void setAccessStatusId(Long accessStatusId) {
        this.accessStatusId = accessStatusId;
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

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

}

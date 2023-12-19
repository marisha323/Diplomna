package com.example.Diplomna.Controller;

import org.springframework.web.multipart.MultipartFile;

public class NewVideoRepr {

    private String description;
    private String title;

    private MultipartFile file;

    public NewVideoRepr() {
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
}

package com.example.Diplomna.classValid;

import org.springframework.web.multipart.MultipartFile;

public class AddImgUser {
    private MultipartFile photoUrl;

    public AddImgUser() {
    }

    public MultipartFile getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(MultipartFile photoUrl) {
        this.photoUrl = photoUrl;
    }
}

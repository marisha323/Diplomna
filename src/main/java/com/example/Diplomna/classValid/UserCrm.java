package com.example.Diplomna.classValid;

import jakarta.persistence.Column;
import org.springframework.web.multipart.MultipartFile;

public class UserCrm {

    private String userName;

    private String email;

    //private String password;

    private MultipartFile photoUrl;

    public MultipartFile getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(MultipartFile photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }


}

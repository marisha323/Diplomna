package com.example.Diplomna.classValid;

public class UserDTO {
    private Long id;

    private Long userId;
    private String userName;
    private String photoUrl;
    private byte[] photoByte;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserDTO() {
    }

    public UserDTO(Long Id, String userName, String photoUrl, byte[] avatarBytes) {
        this.userId=Id;
        this.userName = userName;
        this.photoUrl = photoUrl;
        this.photoByte=avatarBytes;
    }

    public byte[] getPhotoByte() {
        return photoByte;
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

    public void setPhotoByte(byte[] photoByte) {
        this.photoByte = photoByte;
    }


}

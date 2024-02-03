package com.example.Diplomna.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Channel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;


    @OneToOne
    @JoinColumn(name = "ownerId")
    private User user;
    private String bannerPath;
//    private String description;

    public Channel(Long id, User user, String bannerPath, String description) {
        this.id = id;
        this.user = user;
        this.bannerPath = bannerPath;

    }

    public Long getUser() {
        return user != null ? user.getId() : null;
    }

    public void setUser(Long userId) {
        if (userId != null) {
            User user = new User();
            user.setId(userId);
            this.user = user;
        } else {
            this.user = null;
        }
    }
    public Channel() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getBannerPath() {
        return bannerPath;
    }

    public void setBannerPath(String bannerPath) {
        this.bannerPath = bannerPath;
    }

}

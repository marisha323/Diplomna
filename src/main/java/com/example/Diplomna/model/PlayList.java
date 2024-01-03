package com.example.Diplomna.model;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
public class PlayList implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @ManyToOne
    @JoinColumn(name = "accessStatusId")
    private AccessStatus accessStatus;

    public PlayList() {

    }

    @Override
    public String toString() {
        return "PlayList{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", user=" + user +
                ", accessStatus=" + accessStatus +
                '}';
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

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
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

    public AccessStatus getAccessStatus() {
        return accessStatus;
    }

    public void setAccessStatus(AccessStatus accessStatus) {
        this.accessStatus = accessStatus;
    }

    public PlayList(Long id, String title, User user, AccessStatus accessStatus) {
        this.id = id;
        this.title = title;
        this.user = user;
        this.accessStatus = accessStatus;
    }
}

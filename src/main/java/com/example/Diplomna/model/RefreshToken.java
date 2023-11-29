package com.example.Diplomna.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class RefreshToken implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    private String refreshToken;

    public RefreshToken() {

    }

    @Override
    public String toString() {
        return "RefreshToken{" +
                "id=" + id +
                ", user=" + user +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public RefreshToken(Long id, User user, String refreshToken) {
        this.id = id;
        this.user = user;
        this.refreshToken = refreshToken;
    }
}

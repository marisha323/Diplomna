package com.example.Diplomna.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Channel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    @OneToOne
    @JoinColumn(name = "ownerId")
    private User user;

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", user=" + user +
                ", description='" + description + '\'' +
                ", bannerId=" + bannerId +
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getBannerId() {
        return bannerId;
    }

    public void setBannerId(Long bannerId) {
        this.bannerId = bannerId;
    }

    public Channel(Long id, User user, String description, Long bannerId) {
        this.id = id;
        this.user = user;
        this.description = description;
        this.bannerId = bannerId;
    }

    private String description;
    private Long bannerId;
}

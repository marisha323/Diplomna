package com.example.Diplomna.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class ActivationLink implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    private LocalDateTime expireDateTime;
    private String link;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public ActivationLink() {

    }

    @Override
    public String toString() {
        return "ActivationLink{" +
                "id=" + id +
                ", expireDateTime=" + expireDateTime +
                ", link='" + link + '\'' +
                ", user=" + user +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getExpireDateTime() {
        return expireDateTime;
    }

    public void setExpireDateTime(LocalDateTime expireDateTime) {
        this.expireDateTime = expireDateTime;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ActivationLink(Long id, LocalDateTime expireDateTime, String link, User user) {
        this.id = id;
        this.expireDateTime = expireDateTime;
        this.link = link;
        this.user = user;
    }

}

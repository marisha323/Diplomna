package com.example.Diplomna.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class SupportReview implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    private String Theme;

    public SupportReview() {
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

    public String getTheme() {
        return Theme;
    }

    public void setTheme(String theme) {
        Theme = theme;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public SupportReviewStatus getSupportReviewStatusId() {
        return SupportReviewStatusId;
    }

    public void setSupportReviewStatusId(SupportReviewStatus supportReviewStatusId) {
        SupportReviewStatusId = supportReviewStatusId;
    }

    private String Message;

    public SupportReview(User user, String theme, String message, SupportReviewStatus supportReviewStatusId) {
        this.user = user;
        Theme = theme;
        Message = message;
        SupportReviewStatusId = supportReviewStatusId;
    }

    @OneToOne
    @JoinColumn(name = "SupportReviewStatusId")
    private SupportReviewStatus SupportReviewStatusId;
}

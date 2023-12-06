package com.example.Diplomna.model;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
public class SupportReviewStatus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    private String title;
    @OneToOne
    @JoinColumn(name = "SupportReviewId")
    private SupportReview SupportReviewId;

    public SupportReviewStatus(Long id, String title, SupportReview supportReviewId) {
        this.id = id;
        this.title = title;
        SupportReviewId = supportReviewId;
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

    public SupportReview getSupportReviewId() {
        return SupportReviewId;
    }

    public void setSupportReviewId(SupportReview supportReviewId) {
        SupportReviewId = supportReviewId;
    }

    @Override
    public String toString() {
        return "SupportReviewStatus{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", SupportReviewId=" + SupportReviewId +
                '}';
    }
}

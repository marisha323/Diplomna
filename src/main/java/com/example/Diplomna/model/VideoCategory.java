package com.example.Diplomna.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class VideoCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;
    private String title;

    public void setId(Long videoCategoryId) {
        this.id=videoCategoryId;
    }

    public Long getId() {

        return this.id;
    }
}

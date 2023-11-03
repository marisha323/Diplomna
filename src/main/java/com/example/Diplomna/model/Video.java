package com.example.Diplomna.model;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
public class Video implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    private String title;
    private String description;
    private String path;
    private Long ownerId;
    private Long accessStatusId;
    private Long previewId;
    private Long views;
}

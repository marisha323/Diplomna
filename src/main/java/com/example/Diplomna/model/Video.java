package com.example.Diplomna.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;

@Entity
public class Video implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;
    private String title;
    private String description;
    private String path;
    @ManyToOne
    @JoinColumn(name = "ownerId")
    private User user;
    @ManyToOne
    @JoinColumn(name = "accessStatusId")
    private AccessStatus accessStatus;
//?????????
    //    @ManyToOne
//    @JoinColumn(name = "previewId")
//    private User user;
//    private Long previewId;
    private Long views;
    @ManyToOne
    @JoinColumn(name = "videoCategoryId")
    private VideoCategory videoCategory;

    private LocalDateTime uploadDate;
    private Time time;
}

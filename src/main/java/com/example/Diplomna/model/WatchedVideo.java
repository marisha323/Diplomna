package com.example.Diplomna.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class WatchedVideo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "userId")

    private User user;
    @ManyToOne
    @JoinColumn(name = "videoId")
    private Video video;

    private int watchCount;
    private LocalDateTime watchDate;
    @ManyToOne
    @JoinColumn(name = "gradeId")
    private Grade grade;

    private LocalDateTime gradeDate;



}

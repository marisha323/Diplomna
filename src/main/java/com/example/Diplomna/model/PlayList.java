package com.example.Diplomna.model;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
public class PlayList implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    private Long userId;
    private String title;
    private Long AccessStatusId;

}

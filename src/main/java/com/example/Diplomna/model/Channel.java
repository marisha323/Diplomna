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
    @OneToOne
    @JoinColumn(name = "bannerId")
    private File banner;
    private String description;


}

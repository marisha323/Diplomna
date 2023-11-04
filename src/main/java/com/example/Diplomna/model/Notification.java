package com.example.Diplomna.model;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
public class Notification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    private Long targetUserId;
    private Long notificationTypeId;
    private boolean isNew;

}

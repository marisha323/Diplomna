package com.example.Diplomna.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.text.DateFormat;
import java.time.LocalDateTime;

@Entity
public class Unsubscribe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    private Long userId;
    private Long targetUserId;
    private LocalDateTime dateTime;
}

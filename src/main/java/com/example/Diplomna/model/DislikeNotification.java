package com.example.Diplomna.model;

import com.example.Diplomna.contrscts.INotification;
import jakarta.persistence.*;

import java.io.Serializable;
@Entity
public class DislikeNotification implements Serializable, INotification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "dislikeId")
    private DisLike disLike;

    @Override
    public String toString() {
        return "DislikeNotification{" +
                "id=" + id +
                ", disLike=" + disLike +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DisLike getDisLike() {
        return disLike;
    }

    public void setDisLike(DisLike disLike) {
        this.disLike = disLike;
    }

    public DislikeNotification(Long id, DisLike disLike) {
        this.id = id;
        this.disLike = disLike;
    }
}

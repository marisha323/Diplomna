package com.example.Diplomna.model;

import com.example.Diplomna.contrscts.INotification;
import jakarta.persistence.*;

import java.io.Serializable;
@Entity
public class SubscribeNotification implements Serializable, INotification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "subscribeId")
    private Subscribe subscribe;

    @Override
    public String toString() {
        return "SubscribeNotification{" +
                "id=" + id +
                ", subscribe=" + subscribe +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Subscribe getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Subscribe subscribe) {
        this.subscribe = subscribe;
    }

    public SubscribeNotification(Long id, Subscribe subscribe) {
        this.id = id;
        this.subscribe = subscribe;
    }
}

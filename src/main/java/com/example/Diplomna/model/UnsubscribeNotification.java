package com.example.Diplomna.model;

import com.example.Diplomna.contrscts.INotification;
import jakarta.persistence.*;

import java.io.Serializable;
@Entity
public class UnsubscribeNotification implements Serializable, INotification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "unsubscribeId")
    private Unsubscribe unsubscribe;

    @Override
    public String toString() {
        return "UnsubscribeNotification{" +
                "id=" + id +
                ", unsubscribe=" + unsubscribe +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Unsubscribe getUnsubscribe() {
        return unsubscribe;
    }

    public void setUnsubscribe(Unsubscribe unsubscribe) {
        this.unsubscribe = unsubscribe;
    }

    public UnsubscribeNotification(Long id, Unsubscribe unsubscribe) {
        this.id = id;
        this.unsubscribe = unsubscribe;
    }
}

package com.example.Diplomna.model;

import com.example.Diplomna.contrscts.INotification;
import jakarta.persistence.*;

import java.io.Serializable;
@Entity
public class CommentNotification implements Serializable, INotification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "commentId")
    private Comment comment;

    @Override
    public String toString() {
        return "CommentNotification{" +
                "id=" + id +
                ", comment=" + comment +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public CommentNotification(Long id, Comment comment) {
        this.id = id;
        this.comment = comment;
    }
}

package com.example.Diplomna.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class HashTagVideo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "hashtagId")
    private Hashtag hashtag;
    @ManyToOne
    @JoinColumn(name = "videoId")
    private Video video;

    public HashTagVideo() {

    }

    @Override
    public String toString() {
        return "HashTagVideo{" +
                "id=" + id +
                ", hashtag=" + hashtag +
                ", video=" + video +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hashtag getHashtag() {
        return hashtag;
    }

    public void setHashtag(Hashtag hashtag) {
        this.hashtag = hashtag;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public HashTagVideo(Long id, Hashtag hashtag, Video video) {
        this.id = id;
        this.hashtag = hashtag;
        this.video = video;
    }
}

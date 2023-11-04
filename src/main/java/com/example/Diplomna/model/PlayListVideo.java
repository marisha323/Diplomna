package com.example.Diplomna.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class PlayListVideo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "playListId")
    private PlayList playList;
    @ManyToOne
    @JoinColumn(name = "videoId")
    private Video video;

    @Override
    public String toString() {
        return "PlayListVideo{" +
                "id=" + id +
                ", playList=" + playList +
                ", video=" + video +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlayList getPlayList() {
        return playList;
    }

    public void setPlayList(PlayList playList) {
        this.playList = playList;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public PlayListVideo(Long id, PlayList playList, Video video) {
        this.id = id;
        this.playList = playList;
        this.video = video;
    }
}

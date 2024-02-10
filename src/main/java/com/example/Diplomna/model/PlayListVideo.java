package com.example.Diplomna.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity

public class PlayListVideo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "playListId")
    private PlayList playList;
    @ManyToOne
    @JoinColumn(name = "videoId")
    private Video video;

    public PlayListVideo() {

    }

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

//    public PlayList getPlayList() {
//        return playList;
//    }
//
//    public void setPlayList(PlayList playList) {
//        this.playList = playList;
//    }

    public Long getPlayList() {
        return playList != null ? playList.getId() : null;
    }

    public void setPlayList(Long playListId) {
        if (playListId != null) {
            PlayList playList = new PlayList();
            playList.setId(playListId);
            this.playList = playList;
        } else {
            this.playList = null;
        }
    }

//    public Video getVideo() {
//        return video;
//    }
//
//    public void setVideo(Video video) {
//        this.video = video;
//    }
    public Long getVideo() {
        return video != null ? video.getId() : null;
    }

    public void setVideo(Long videoId) {
        if (videoId != null) {
            Video video = new Video();
            video.setId(videoId);
            this.video = video;
        } else {
            this.video = null;
        }
    }

    public PlayListVideo(Long id, PlayList playList, Video video) {
        this.id = id;
        this.playList = playList;
        this.video = video;
    }
}

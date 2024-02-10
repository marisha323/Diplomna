package com.example.Diplomna.GrabePicture;

import com.example.Diplomna.model.User;
import com.example.Diplomna.model.Video;

import java.util.List;

public class UserWithVideosResponse {
    private User user;
    private List<Video> videos;

    public UserWithVideosResponse(User user, List<Video> videos) {
        this.user = user;
        this.videos = videos;
    }
}

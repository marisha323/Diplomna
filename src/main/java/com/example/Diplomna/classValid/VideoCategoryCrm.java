package com.example.Diplomna.classValid;

import lombok.Getter;

@Getter
public class VideoCategoryCrm {
    Long id;
    String title;

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

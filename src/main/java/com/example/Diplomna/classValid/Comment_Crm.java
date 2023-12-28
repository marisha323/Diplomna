package com.example.Diplomna.classValid;

import java.time.LocalDateTime;

public class Comment_Crm {
    private String text;
    private Long video_id;

    public Long getVideo_id() {
        return video_id;
    }

    public void setVideo_id(Long video_id) {
        this.video_id = video_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

package com.example.Diplomna.dto;


import com.example.Diplomna.model.Video;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubscribersVideoDto {
    private Long id;
    private String title;
    private String description;
    private String uri;
    private Long views;

    private UserDto user;
}

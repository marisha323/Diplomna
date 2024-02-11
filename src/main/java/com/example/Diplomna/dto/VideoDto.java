package com.example.Diplomna.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideoDto {
    private Long id;
    private String title;
    private String description;
    private String uri;
    private Long views;
    private AccessStatusDto accessStatus;
    private VideoCategoryDto videoCategory;
    private LocalDateTime uploadDate;
    private Time time;

    private UserDto user;
}

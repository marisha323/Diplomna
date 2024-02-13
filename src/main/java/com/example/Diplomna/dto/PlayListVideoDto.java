package com.example.Diplomna.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayListVideoDto {
    private Long id;
    private String title;
    private String description;
    private String uri;
    private Long views;

    private UserDto user;
}

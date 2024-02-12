package com.example.Diplomna.dto;

import com.example.Diplomna.model.AccessStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistForVideoDto {
    private Long id;
    private String title;
    private Boolean alreadyAdded;
    private AccessStatus accessStatus;
}

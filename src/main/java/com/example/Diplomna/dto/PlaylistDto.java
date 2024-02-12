package com.example.Diplomna.dto;

import com.example.Diplomna.model.AccessStatus;
import com.example.Diplomna.model.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistDto {
    private Long id;
    private String title;
    private int videoCount;
    private AccessStatus accessStatus;
}

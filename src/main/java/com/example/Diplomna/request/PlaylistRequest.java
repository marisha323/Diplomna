package com.example.Diplomna.request;

import com.example.Diplomna.model.AccessStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistRequest {
   private Long id;
   private String title;
   private AccessStatus accessStatus;
}

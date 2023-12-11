package com.example.Diplomna.auth.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoogleAuthRequest {
    private String uid;
    private String email;
    private boolean emailVerified;
    private String displayName;
    private String photoUrl;
}

package com.example.Diplomna.auth.services;

import com.example.Diplomna.auth.request.GoogleAuthRequest;
import com.example.Diplomna.auth.response.AuthenticationResponse;
import com.example.Diplomna.config.JwtService;
import com.example.Diplomna.dto.UserDto;
import com.example.Diplomna.enums.Role;
import com.example.Diplomna.model.User;
import com.example.Diplomna.repo.UserRepo;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class GoogleAuthService {
    private final JwtService jwtService;
    private final UserRepo userRepo;
    public Object login(GoogleAuthRequest  request) throws Exception {
        if (!request.isEmailVerified()) {
            throw new Exception("Email not verified");
        }
        if (userRepo.existsByEmail(request.getEmail())) {
            return authenticate(request);
        }

        return register(request);
    }

    private Object register(GoogleAuthRequest request) {
        User user = User.builder()
                .userName(request.getDisplayName())
                .email(request.getEmail())
                .externalId(request.getUid())
                .photoUrl(request.getPhotoUrl())
                .role(Role.USER)
                .isActivated(true)
                .build();
        userRepo.save(user);

        return createAuthenticationResponse(user);
    }

    private Object authenticate(GoogleAuthRequest request) throws Exception {
        User user = userRepo.findByEmail(request.getEmail()).orElseThrow();
        if (!user.getExternalId().equals(request.getUid())) {
            throw new Exception("User already exists");
        }
        return createAuthenticationResponse(user);
    }

    private AuthenticationResponse createAuthenticationResponse(UserDetails user) {
        return AuthenticationResponse.create(user, userRepo);
    }

    public GoogleAuthRequest parseRequest(String jsonStringRequest) throws Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(jsonStringRequest, GoogleAuthRequest.class);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

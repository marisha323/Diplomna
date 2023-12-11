package com.example.Diplomna.auth.controllers;

import com.example.Diplomna.auth.request.GoogleAuthRequest;
import com.example.Diplomna.auth.services.GoogleAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/google")
public class GoogleAuthController {
    private final GoogleAuthService googleAuthService;
    @PostMapping("/login")
    public ResponseEntity<Object> googleAuth(
            @RequestBody GoogleAuthRequest request
            ) {
        try {
            return ResponseEntity.ok(googleAuthService.login(request));
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login-test")
    public ResponseEntity<Object> googleAuth(String request) {
        try {
            GoogleAuthRequest googleAuthRequest = googleAuthService.parseRequest(request);
            return ResponseEntity.ok(googleAuthService.login(googleAuthRequest));
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

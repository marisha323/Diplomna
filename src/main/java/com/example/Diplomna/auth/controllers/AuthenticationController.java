package com.example.Diplomna.auth.controllers;

import com.example.Diplomna.auth.response.AuthenticationResponse;
import com.example.Diplomna.auth.services.AuthenticationService;
import com.example.Diplomna.auth.request.AuthenticationRequest;
import com.example.Diplomna.auth.request.RegisterRequest;
import com.example.Diplomna.auth.request.ReloginRequest;
import com.example.Diplomna.services.ActivationLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final ActivationLinkService activationLinkService;
    @PostMapping("/register")
    public ResponseEntity<Object> register (
            @RequestBody RegisterRequest request
    )
    {
        try {
            return ResponseEntity.ok(authenticationService.register(request));
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthenticationResponse> authenticate (
            @RequestBody AuthenticationRequest request
    ) throws Exception {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refresh-auth")
    public ResponseEntity<Object> refreshAuthentication(@RequestBody ReloginRequest request) {
        try {
            return ResponseEntity.ok(authenticationService.relogin(request));
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/activate")
    public ResponseEntity<Object> activate(Long user_id, String code) {
        try {
            return ResponseEntity.ok(activationLinkService.activate(user_id, code));
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}

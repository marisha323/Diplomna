package com.example.Diplomna.auth.services;

import com.example.Diplomna.auth.request.AuthenticationRequest;
import com.example.Diplomna.auth.request.RegisterRequest;
import com.example.Diplomna.auth.request.ReloginRequest;
import com.example.Diplomna.auth.response.AuthenticationResponse;
import com.example.Diplomna.auth.response.RegisterResponse;
import com.example.Diplomna.config.JwtService;
import com.example.Diplomna.dto.UserDto;
import com.example.Diplomna.enums.Role;
import com.example.Diplomna.model.User;
import com.example.Diplomna.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final MailService mailService;
    private final UserDetailsService userDetailsService;

    public RegisterResponse register(RegisterRequest request) throws Exception {
        if (isUserExistsByEmail(request.getUserEmail())) {
            throw new Exception("User already exist");
        }

        var user = User.builder()
                .userName(request.getUserName())
                .email(request.getUserEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepo.save(user);

        return createRegisterResponse(user);
    }

    private RegisterResponse createRegisterResponse(User user) throws Exception {
        String link = sendActivationLink(user);

        return RegisterResponse.builder()
                .activationLink(link)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepo.findByEmail(request.getEmail())
                .orElseThrow();
        if (!user.isActivated()) {
            throw new Exception("User not activated");
        }

        return createAuthenticationResponse(user);
    }

    private AuthenticationResponse createAuthenticationResponse(UserDetails user) {
        return AuthenticationResponse.create(user, userRepo);
    }

    private boolean isUserExistsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    private String sendActivationLink(User user) throws Exception {
        try {
            return mailService.sendActivationLink(user);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public Object relogin(ReloginRequest request) throws Exception {
        String jwt = request.getRefreshToken();
        String userEmail = jwtService.extractUsername(jwt);
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() != null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                return createAuthenticationResponse(userDetails);
            }
        }
        throw new Exception("RefreshToken Not Valid");
    }
}

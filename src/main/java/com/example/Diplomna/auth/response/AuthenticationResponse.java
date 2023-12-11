package com.example.Diplomna.auth.response;

import com.example.Diplomna.config.JwtService;
import com.example.Diplomna.dto.UserDto;
import com.example.Diplomna.model.User;
import com.example.Diplomna.repo.UserRepo;
import com.example.Diplomna.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class AuthenticationResponse {

    private String accessToken;
    private String refreshToken;
    private UserDto user;

    private static JwtService jwtService;
    private static UserService userService;

    public static AuthenticationResponse create(UserDetails user, UserRepo userRepo) {
        jwtService = new JwtService();
        User userData = userRepo.findByEmail(user.getUsername()).orElseThrow();

        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        var userDto = UserDto.builder()
                .id(userData.getId())
                .displayName(userData.getUserName())
                .email(userData.getEmail())
                .photoUrl(userData.getPhotoUrl())
                .build();

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .user(userDto)
                .build();
    }
}

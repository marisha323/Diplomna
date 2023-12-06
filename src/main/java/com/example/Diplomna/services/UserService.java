package com.example.Diplomna.services;


import com.example.Diplomna.repo.UserRepo;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
}
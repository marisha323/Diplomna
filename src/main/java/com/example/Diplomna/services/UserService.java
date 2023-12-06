package com.example.Diplomna.services;


import com.example.Diplomna.repo.UserRepo;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepo userRepository;

    public UserService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }
}
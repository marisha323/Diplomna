package com.example.Diplomna.services;

import com.example.Diplomna.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepo userRepo;
@Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
}

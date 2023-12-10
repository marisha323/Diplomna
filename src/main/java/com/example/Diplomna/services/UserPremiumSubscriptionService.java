package com.example.Diplomna.services;


import com.example.Diplomna.model.User;
import com.example.Diplomna.model.UserRole;
import com.example.Diplomna.repo.UserPremiumSubscriptionRepo;
import com.example.Diplomna.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserPremiumSubscriptionService {
    @Autowired
    private final UserPremiumSubscriptionRepo userPremiumSubscriptionRepo;

    public UserPremiumSubscriptionService(UserPremiumSubscriptionRepo userPremiumSubscriptionRepo) {
        this.userPremiumSubscriptionRepo = userPremiumSubscriptionRepo;
    }


}

package com.example.Diplomna.services;

import com.example.Diplomna.repo.UserPremiumSubscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPremiumSubscriptionService {
    @Autowired
    private final UserPremiumSubscriptionRepo userPremiumSubscriptionRepo;

    public UserPremiumSubscriptionService(UserPremiumSubscriptionRepo userPremiumSubscriptionRepo) {
        this.userPremiumSubscriptionRepo = userPremiumSubscriptionRepo;
    }
}

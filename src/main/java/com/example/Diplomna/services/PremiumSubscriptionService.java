package com.example.Diplomna.services;

import com.example.Diplomna.repo.PremiumSubscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PremiumSubscriptionService {

    @Autowired
    private final PremiumSubscriptionRepo premiumSubscriptionRepo;

    public PremiumSubscriptionService(PremiumSubscriptionRepo premiumSubscriptionRepo) {
        this.premiumSubscriptionRepo = premiumSubscriptionRepo;
    }
}

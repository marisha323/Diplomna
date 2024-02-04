package com.example.Diplomna.services;

import com.example.Diplomna.repo.SubscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {
    private final SubscriptionRepo subscriptionRepo;

    @Autowired
    public SubscriptionService(SubscriptionRepo subscriptionRepo) {
        this.subscriptionRepo = subscriptionRepo;
    }


    public long countVideoOfMyChannel(Long id) {
        return subscriptionRepo.countSubscribers(id);
    }

}

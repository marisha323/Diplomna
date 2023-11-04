package com.example.Diplomna.services;

import com.example.Diplomna.repo.SubscribeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscribeService {
    private final SubscribeRepo subscribeRepo;

    @Autowired
    public SubscribeService(SubscribeRepo subscribeRepo) {
        this.subscribeRepo = subscribeRepo;
    }
}

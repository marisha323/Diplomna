package com.example.Diplomna.services;

import com.example.Diplomna.repo.UnsubscribeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnsubscribeService {
    private final UnsubscribeRepo unsubscribeRepo;
    @Autowired
    public UnsubscribeService(UnsubscribeRepo unsubscribeRepo) {
        this.unsubscribeRepo = unsubscribeRepo;
    }
}

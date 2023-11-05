package com.example.Diplomna.services;

import com.example.Diplomna.repo.AccessStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessStatusService {
    private final AccessStatusRepo accessStatusRepo;

    @Autowired
    public AccessStatusService(AccessStatusRepo accessStatusRepo) {
        this.accessStatusRepo = accessStatusRepo;
    }
}

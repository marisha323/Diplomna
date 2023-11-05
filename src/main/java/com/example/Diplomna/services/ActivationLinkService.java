package com.example.Diplomna.services;
import com.example.Diplomna.repo.ActivationLinkRepo;
import com.example.Diplomna.repo.ChannelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ActivationLinkService {
    private final ActivationLinkRepo activationLinkRepo;
    @Autowired
    public ActivationLinkService(ActivationLinkRepo activationLinkRepo) {
        this.activationLinkRepo = activationLinkRepo;
    }
}
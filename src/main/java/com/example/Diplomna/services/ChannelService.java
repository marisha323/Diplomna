package com.example.Diplomna.services;

import com.example.Diplomna.repo.ChannelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChannelService {
    private final ChannelRepo channelRepo;
    @Autowired
    public ChannelService(ChannelRepo channelRepo) {
        this.channelRepo = channelRepo;
    }
}

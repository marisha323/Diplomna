package com.example.Diplomna.Controller;

import com.example.Diplomna.GrabePicture.ChannelResponseConvert;
import com.example.Diplomna.model.PlayList;
import com.example.Diplomna.model.User;
import com.example.Diplomna.repo.ChannelRepo;
import com.example.Diplomna.services.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/channels")
public class ChannelController {
    @Autowired
    ChannelService channelService;
    ChannelRepo channelRepo;

    @GetMapping("/channel-user")
    public List<ChannelResponseConvert> channelUser(@RequestHeader("Authorization") String authorizationHeader) {

        return channelService.chanelUser(authorizationHeader);

    }

    @GetMapping("/channel-user-video")
    public List<?> channelUserVideo(@RequestHeader("Authorization") String authorizationHeader) {
        return channelService.getAllVideosForUser(authorizationHeader);
    }
}

package com.example.Diplomna.services;

import com.example.Diplomna.GrabePicture.ChannelResponseConvert;
import com.example.Diplomna.GrabePicture.VideoWithUserInfo;
import com.example.Diplomna.classValid.CrmHelper;
import com.example.Diplomna.enums.NotFoundException;
import com.example.Diplomna.model.Channel;
import com.example.Diplomna.model.User;
import com.example.Diplomna.repo.ChannelRepo;
import com.example.Diplomna.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ChannelService {
    private final ChannelRepo channelRepo;
    private static UserRepo userRepo;
    private final Logger logger = LoggerFactory.getLogger(PlayListService.class);
    private ChannelResponseConvert channelResponseConvert;


    @Autowired
    public ChannelService(ChannelRepo channelRepo, UserRepo userRepo) {
        this.channelRepo = channelRepo;
        this.userRepo = userRepo;
    }
    public static byte[] downloadAvaUser(Long id) throws IOException {
        User user = userRepo.findById(id).orElseThrow(() -> new NotFoundException());

        return Files.readAllBytes(new File(user.getPhotoUrl()).toPath());
    }
    private static ChannelResponseConvert convert(Channel banner, User user) {
        ChannelResponseConvert repr = new ChannelResponseConvert();
        repr.setUserName(user.getUserName());
        repr.setPhotoUrl(user.getPhotoUrl());
        repr.setBannerUrl(banner.getBannerPath());

        try {
            byte[] avatarBytes = downloadAvaUser(user.getId());
            repr.setAvatarBytes(avatarBytes);
        } catch (IOException e) {
            // Обробити помилку завантаження аватарки
            e.printStackTrace();
        }

        return repr;


    }
    public List<ChannelResponseConvert> chanelUser(String authorizationHeader) {
        CrmHelper crmHelper = new CrmHelper(userRepo);
        Long userId = crmHelper.userId(authorizationHeader);
        logger.info("userId " + userId);

        List<ChannelResponseConvert> subscribedVideos = channelRepo.findChannelsByUser_Id(userId)
                .stream()
                .flatMap(channel -> {
                    User user = userRepo.findById(channel.getUser()).orElse(null);
                    if (user != null) {
                        return Stream.of(convert(channel, user));
                    } else {
                        return Stream.empty();
                    }
                })
                .collect(Collectors.toList());

        return subscribedVideos;
    }

    public Channel createChannel(String authorizationHeader) {
        CrmHelper crmHelper = new CrmHelper(userRepo);
        Long userId = crmHelper.userId(authorizationHeader);
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Channel channel = new Channel();
        channel.setUser(user.getId());
        channel.setBannerPath("DEFAULT_BANNER_PATH");
        return channelRepo.save(channel);
    }

}

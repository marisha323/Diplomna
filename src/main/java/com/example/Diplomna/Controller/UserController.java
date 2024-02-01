package com.example.Diplomna.Controller;

import com.example.Diplomna.classValid.AddImgUser;
import com.example.Diplomna.classValid.CrmHelper;
import com.example.Diplomna.classValid.UserCrm;
import com.example.Diplomna.model.Channel;
import com.example.Diplomna.model.User;
import com.example.Diplomna.repo.ChannelRepo;
import com.example.Diplomna.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.Diplomna.model.User;
import com.example.Diplomna.classValid.CrmHelper;
import com.example.Diplomna.repo.UserRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Diplomna.services.UserService;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ChannelRepo channelRepo;
    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping(path = "/update-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateImageUrl(@RequestHeader("Authorization") String authorizationHeader,AddImgUser user) {
        CrmHelper crmHelper = new CrmHelper(userRepo);
        Long userId = crmHelper.userId(authorizationHeader);
        if (userId != null) {
            Optional<User> userOptional = userRepo.findById(userId);
            if (userOptional.isPresent()) {
                User usermod = userOptional.get();
                MultipartFile file = user.getPhotoUrl();
                String imageUrl = String.valueOf(userService.addImgAva(file));
                logger.info("Service File Name: " + imageUrl);
                logger.info("UserId: " + userId);
                usermod.setPhotoUrl(imageUrl);
                userRepo.save(usermod);
                return imageUrl;
            } else {
                return "User not found";
            }
        } else {
            return "Unauthorized";
        }
    }
    @GetMapping("/getUserId")
    public Long getUserId(@RequestHeader("Authorization") String authorizationHeader) {
        CrmHelper crmHelper = new CrmHelper(userRepo);

        return crmHelper.userId(authorizationHeader);
    }

    @PostMapping(path = "/editblala", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<User> editUser(@RequestHeader("Authorization") String authorizationHeader,
                                          UserCrm editedUser ) throws IOException {
logger.info("nriihjihni4thnhh");
            User updatedUser = userService.editUser(authorizationHeader, editedUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);

    }

    @PostMapping(path = "/update-banner", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateBanner(@RequestHeader("Authorization") String authorizationHeader, AddImgUser user) {
        CrmHelper crmHelper = new CrmHelper(userRepo);
        Long userId = crmHelper.userId(authorizationHeader);

        if (userId != null) {
           Optional<Channel> channelOptional = channelRepo.findByUserId(userId);

            if (channelOptional.isPresent()) {
                Channel channel = channelOptional.get();
                MultipartFile file = user.getPhotoUrl();
                String imageUrl = String.valueOf(userService.addImgAva(file));

                logger.info("Service File Name: " + imageUrl);
                logger.info("UserId: " + userId);

                // Оновлення шляху банера для користувача
                channel.setBannerPath(imageUrl);
                channelRepo.save(channel);

                return imageUrl;
            } else {
                return "User or Channel not found";
            }
        } else {
            return "Unauthorized";
        }
    }


}

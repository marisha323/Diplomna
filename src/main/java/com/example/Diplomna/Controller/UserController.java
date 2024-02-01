package com.example.Diplomna.Controller;

import com.example.Diplomna.classValid.AddImgUser;
import com.example.Diplomna.classValid.CrmHelper;
import com.example.Diplomna.classValid.UserCrm;
import com.example.Diplomna.model.User;
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

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserRepo userRepo;
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

    @PutMapping("/edit")
    public ResponseEntity<User> editUser(@RequestHeader("Authorization") String authorizationHeader,
                                         @RequestBody UserCrm editedUser           ) {
        try {
            User updatedUser = userService.editUser(authorizationHeader, editedUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

package com.example.Diplomna.Controller;

import com.example.Diplomna.classValid.AddImgUser;
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

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping(path = "/update-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateImageUrl(AddImgUser user) {
        try {
            MultipartFile file = user.getPhotoUrl();
            String imageUrl = String.valueOf(userService.addImgAva(file));
            logger.info("Service File Name: " + imageUrl);
            User saveuse=new User();

            saveuse.setPhotoUrl(imageUrl);
            return imageUrl;
        } catch (Exception ex) {
            ex.printStackTrace();
            // You can handle the exception accordingly, e.g., log it or return an error message
            throw new RuntimeException("Failed to update image URL", ex);
        }
    }
}

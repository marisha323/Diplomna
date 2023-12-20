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

import com.example.Diplomna.model.User;
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

    @Autowired
    private UserService userService;


    private UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    private static final String SECRET_KEY = "438cfffbf78a11313266c90207250b6a863db87595ecf9c6841562223cb3aa41";
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);



    @GetMapping("/getUserId")
    public Long getUserId(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            logger.info("Auth: " + authorizationHeader);
            // Виділіть токен з Authorization заголовку
            String token = authorizationHeader.substring("Bearer ".length());

            // Розкодуйте токен і отримайте ідентифікатор користувача
            String email = decodeTokenAndGetUserId(token);
            logger.info("email: "+ email);


            Optional<User> user = userRepo.findByEmail(email);
            logger.info("User: "+ user);

            // Перевірте, чи користувач існує

                //Long userId = user.get().getId();
                if (user.isPresent()) {
                    // Витягніть ідентифікатор користувача
                    User userId = user.get();
                    logger.info("userId: "+ userId);
                    return userId.getId();

            } else {
                // Обробка випадку, коли користувача не знайдено
                return null; // або можна обробити якщо користувача не знайдено
            }

        } catch (DataAccessException ex) {
            // Handle database-related exceptions
            throw new RuntimeException("Failed to access the database", ex);
        } catch (Exception ex) {
            // Handle other exceptions
            throw new RuntimeException("An unexpected error occurred", ex);
        }
    }

    private String decodeTokenAndGetUserId(String token) {
        // Розкодуйте токен і отримайте його клейми

        logger.info("TOKEN: "+ token);
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        logger.info("CLAIMS: "+ claims);
        String userIdAsString = claims.get("sub", String.class);
        // Отримайте ідентифікатор користувача з клеймів
        return userIdAsString;
    }

}

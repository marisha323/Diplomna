package com.example.Diplomna.classValid;

import com.example.Diplomna.Controller.UserController;
import com.example.Diplomna.model.User;
import com.example.Diplomna.repo.UserRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Optional;

public class CrmHelper {
    private static final String SECRET_KEY = "438cfffbf78a11313266c90207250b6a863db87595ecf9c6841562223cb3aa41";
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserRepo userRepo;

    public CrmHelper(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    private  String decodeTokenAndGetUserId(String token) {
        // Розкодуйте токен і отримайте його клейми
        logger.info("TOKEN: " + token);
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        logger.info("CLAIMS: " + claims);
        return claims.get("sub", String.class);  // Отримайте ідентифікатор користувача з клеймів
    }

    public  Long userId(String authorizationHeader) {
        try {

            logger.info("Auth: " + authorizationHeader);
            // Виділіть токен з Authorization заголовку
            String token = authorizationHeader.substring("Bearer ".length());

            // Розкодуйте токен і отримайте ідентифікатор користувача
            String email = decodeTokenAndGetUserId(token);
            logger.info("email: " + email);

            Optional<User> user = userRepo.findByEmail(email);
            logger.info("User: " + user);

            // Перевірте, чи користувач існує
            if (user.isPresent()) {
                // Витягніть ідентифікатор користувача
                User userId = user.get();
                logger.info("userId: " + userId);
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
}

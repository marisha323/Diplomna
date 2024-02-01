package com.example.Diplomna.services;


import com.example.Diplomna.classValid.AddImgUser;
import com.example.Diplomna.classValid.CrmHelper;
import com.example.Diplomna.classValid.UserCrm;
import com.example.Diplomna.enums.NotFoundException;
import com.example.Diplomna.model.User;
import com.example.Diplomna.repo.UserRepo;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepo userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public String addImgAva(MultipartFile file) {
        try {
            String folderPath = "src/main/resources/user/";
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(folderPath, fileName);
            file.transferTo(filePath);
            return filePath.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload the file." + HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }


public User editUser(String authorizationHeader, UserCrm editedUser) throws IOException {
logger.info("authorizationHeader: "+ authorizationHeader);
    Long userId = CrmHelper.userId(authorizationHeader);
    Optional<User> userOptional = userRepository.findById(userId);
    if (userOptional.isPresent()) {
        User existingUser = userOptional.get();

        // Останні зміни
        existingUser.setUserName(editedUser.getUserName());
        existingUser.setEmail(editedUser.getEmail());
        //existingUser.setPassword(editedUser.getPassword());
        existingUser.setExternalId("1");
        existingUser.setActivated(true);
        logger.info("USER NAME" + existingUser.getUserName());
        // IMAGE
        MultipartFile file = editedUser.getPhotoUrl();
        if (file != null && !file.isEmpty()) {
            String folderPath = "src/main/resources/user/";
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(folderPath, fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            existingUser.setPhotoUrl(fileName);
        }

        return userRepository.save(existingUser);
    } else {
        throw new NotFoundException("User not found");
    }
}

}




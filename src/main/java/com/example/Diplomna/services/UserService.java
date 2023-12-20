package com.example.Diplomna.services;


import com.example.Diplomna.model.User;
import com.example.Diplomna.repo.UserRepo;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public String addImgAva(MultipartFile file){
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
            return "Failed to upload the file."+ HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

}
package com.example.Diplomna.services;


import com.example.Diplomna.GrabePicture.UserWithVideosResponse;
import com.example.Diplomna.classValid.CrmHelper;
import com.example.Diplomna.model.User;
import com.example.Diplomna.model.Video;
import com.example.Diplomna.repo.UserRepo;

import com.example.Diplomna.repo.VideoRepo;
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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private static UserRepo userRepo;
    private VideoRepo videoRepo;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    public UserService(UserRepo userRepo, VideoRepo videoRepo) {
        this.userRepo = userRepo;
        this.videoRepo = videoRepo;
    }

    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
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

    public Optional<User> findUserById(Long userId) {
        return userRepo.findById(userId);
    }

}

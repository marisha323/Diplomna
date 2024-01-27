package com.example.Diplomna.services;


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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;

@Service
@RequiredArgsConstructor
public class UserService {


    private static UserRepo userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }
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

    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }


//    private static byte[] toByteArray(InputStream inputStream) throws IOException {
//        return Files.readAllBytes(Paths.get(inputStream.toString()));
//    }

    public static byte[] toByteArray(Long id) throws IOException {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException());

        return Files.readAllBytes(new File(user.getPhotoUrl()).toPath());
    }

}

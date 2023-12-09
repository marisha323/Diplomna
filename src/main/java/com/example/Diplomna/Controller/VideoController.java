package com.example.Diplomna.Controller;

import com.sun.net.httpserver.HttpExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/video")
public class VideoController {
    private static final Logger logger = LoggerFactory.getLogger(VideoController.class);
    @GetMapping("/add")
    public String addCategory(Model model) {
        return "rdfigjrdifjhidh";
    }

    //    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void uploadVideo(@RequestParam("file")MultipartFile file)
//    {
//
//    }
    @PostMapping("/upload")
    public ResponseEntity<String> handleVideoUpload(@RequestParam("file") MultipartFile file) {
        try {
            // Вказати шлях до папки для збереження файлів
            String folderPath = "src/main/resources/videos/";

            // Переконатися, що папка існує, якщо ні - створити
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Генерувати унікальне ім'я файлу
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            // Створити об'єкт файлу для збереження
            Path filePath = folder.toPath().resolve(fileName);

            // Зберегти файл на сервері
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Генерувати URL для збереженого файлу
            String fileUrl = "http://localhost:8085/video/" + fileName; // Это пример URL, вы можете настроить его по своему усмотрению

            // Сохранить URL в базе данных или выполнить другие необходимые действия

            // Вернуть URL клиенту
            return new ResponseEntity<>(fileUrl, HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to upload the file.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}





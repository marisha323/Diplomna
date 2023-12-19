package com.example.Diplomna.Controller;

import com.example.Diplomna.model.Video;
import com.example.Diplomna.repo.VideoRepo;
import com.example.Diplomna.services.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import java.security.Principal;

@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;
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
    public ResponseEntity<String> handleVideoUpload() {
        return null;
    }

    @GetMapping("/all")
    public List<Video> findAll()
    {
        return null;
    }

    @GetMapping("/{id}")
    public Video findById(@PathVariable("id") long id)
    {
        return null;
    }


    @GetMapping(value = "/preview/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<StreamingResponseBody> getPreviewPicture(@PathVariable("id") Long id) {
        InputStream inputStream = videoService.getPreviewInputStream(id)
                .orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(inputStream::transferTo);
    }

    @GetMapping("/info")
    public String videoData() //інжектимо поточного користувача через принципал
    {
        return "rdfigjrdifjhidh";
    }

    @PostMapping(path = "/uploadNew", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadVideo(NewVideoRepr newVideoRepr) {
        logger.info("Title: " + newVideoRepr.getTitle());
        logger.info("Description: " + newVideoRepr.getDescription());

        MultipartFile file = newVideoRepr.getFile();
        if (file != null) {
            logger.info("File Name: " + file.getOriginalFilename());
            logger.info("Content Type: " + file.getContentType());
            // You might want to log more details depending on your requirements
        }
        try {
            logger.info("Video saved successfully");
            videoService.uploadVideo(file);
        } catch (Exception ex) {
            logger.error("Error saving video", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }



}





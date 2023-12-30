package com.example.Diplomna.Controller;


import com.example.Diplomna.model.Video;
import com.example.Diplomna.GrabePicture.NewVideoRepr;
import com.example.Diplomna.GrabePicture.VideoMetadataRepr;
import com.example.Diplomna.repo.VideoRepo;
import com.example.Diplomna.services.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;
    private VideoRepo videoRepo;
    private static final Logger logger = LoggerFactory.getLogger(VideoController.class);
    @Value("${data.folder")
    private String dataFolder;
    public VideoController(VideoRepo videoRepo)
    {
        this.videoRepo=videoRepo;

    }
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
    public List<VideoMetadataRepr> findAll()
    {
        return videoService.findAll();
    }
    @GetMapping("/{id}")
    public VideoMetadataRepr findVideoMetadataById(@PathVariable("id") Long id) {
        return videoService.findById(id).orElseThrow(NotFoundException::new);
    }
//    @GetMapping(value = "/preview/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
//    public ResponseEntity<byte[]> getPreviewPicture(@PathVariable("id") Long id) {
//        byte[] previewBytes = videoService.getPreviewBytes(id)
//                .orElseThrow(NotFoundException::new);
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.IMAGE_JPEG)
//                .body(previewBytes);
//    }
    @PostMapping(path = "/uploadNew", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadVideo(@RequestHeader("Authorization") String authorizationHeader,NewVideoRepr newVideoRepr) {
        try {
            videoService.uploadVideo(authorizationHeader,newVideoRepr);
            logger.info("Video saved successfully");
        } catch (Exception ex) {
            logger.error("Error saving video", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @ExceptionHandler
    public ResponseEntity<Void> notFoundExceptionHandler(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}





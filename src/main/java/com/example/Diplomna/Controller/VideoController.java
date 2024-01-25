package com.example.Diplomna.Controller;


import com.example.Diplomna.GrabePicture.NewVideoRepr;
import com.example.Diplomna.GrabePicture.VideoMetadataRepr;
import com.example.Diplomna.model.WatchedVideo;
import com.example.Diplomna.repo.VideoRepo;
import com.example.Diplomna.repo.WatchedVideoRepo;
import com.example.Diplomna.services.VideoService;
import com.example.Diplomna.services.WatchedVideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private WatchedVideoService watchedVideoService;
    private VideoRepo videoRepo;

    private WatchedVideoRepo watchedVideoRepo;
    private static final Logger logger = LoggerFactory.getLogger(VideoController.class);
    @Value("${data.folder")
    private String dataFolder;
    public VideoController(VideoRepo videoRepo, WatchedVideoRepo watchedVideoRepo)
    {
        this.videoRepo=videoRepo;

        this.watchedVideoRepo = watchedVideoRepo;
    }

    @GetMapping("/all")
    public List<VideoMetadataRepr> findAll()
    {
        return videoService.findAll(1);
    }
    @GetMapping("/{id}")
    public VideoMetadataRepr findVideoMetadataById(@PathVariable("id") Long id) {
        WatchedVideo watchedVideo = new WatchedVideo();
        watchedVideo.setWatchCount(1);
        watchedVideo.setWatchDate(LocalDateTime.now());
        watchedVideo.setVideo(id);
        watchedVideoRepo.save(watchedVideo);
        return videoService.findById(id).orElseThrow(NotFoundException::new);
    }
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

    @GetMapping("/byteVideo")
    public ResponseEntity<?> downloadVideoByName(String name)  {
        try {
            byte[] videoBytes = videoService.downloadVideo(name);

            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name + ".mp4")
                    .body(videoBytes);
        } catch (NotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



    @ExceptionHandler
    public ResponseEntity<Void> notFoundExceptionHandler(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}





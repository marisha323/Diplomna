package com.example.Diplomna.Controller;

import com.example.Diplomna.GrabePicture.NewVideoRepr;
import com.example.Diplomna.model.Video;
import com.example.Diplomna.model.User;
import com.example.Diplomna.GrabePicture.VideoMetadataRepr;
import com.example.Diplomna.services.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    private static final Logger logger = LoggerFactory.getLogger(VideoController.class);

    @Value("${data.folder")
    private String dataFolder;


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
    public VideoMetadataRepr findVideoMetadataById(@PathVariable("id") Long id) {
        return videoService.findById(id).orElseThrow(NotFoundException::new);
    }


    @GetMapping(value = "/preview/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<StreamingResponseBody> getPreviewPicture(@PathVariable("id") Long id) {
        InputStream inputStream = videoService.getPreviewInputStream(id)
                .orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(inputStream::transferTo);
    }

    @PostMapping(path = "/uploadNew", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadVideo(NewVideoRepr newVideoRepr) {
        logger.info("Title: " + newVideoRepr.getTitle());
        logger.info("Description: " + newVideoRepr.getDescription());

        MultipartFile file = newVideoRepr.getFile();
        if (file != null) {

            logger.info("File Name: " + file.getOriginalFilename());
            logger.info("Content Type: " + file.getContentType());

            String Path= dataFolder+ file.getOriginalFilename();
            logger.info("PATH: " + Path);

            User user= new User();
            user.setId(1L);
            // SAVE TO DB
            Video video = new Video();
            video.setTitle(newVideoRepr.getTitle());
            video.setDescription(newVideoRepr.getDescription());
            video.setPath(Path);
            video.setUser(1L); // Assuming you have a method to get the user from NewVideoRepr
            video.setAccessStatus(1L); // Assuming you have a method to get the access status
            video.setViews(0L); // Initial views count
            video.setVideoCategory(1L); // Assuming you have a method to get the video category
            video.setUploadDate(LocalDateTime.now());
            //video.setTime(Time.valueOf("11:22:55")); // Assuming you have a method to get the time

            // Get video duration

            long duration = getVideoDuration(Path);

            if (duration != -1) {
                logger.info("Тривалість відео: " + duration + " мілісекунд");
            } else {
                logger.info("Не вдалося отримати тривалість відео");
            }
            // Save the Video object to the database
            videoRepo.save(video);


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





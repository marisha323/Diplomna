package com.example.Diplomna.services;

import com.example.Diplomna.GrabePicture.NewVideoRepr;
import com.example.Diplomna.model.User;
import com.example.Diplomna.model.Video;
import com.example.Diplomna.GrabePicture.VideoMetadataRepr;
import com.example.Diplomna.repo.VideoRepo;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//import static java.nio.file.AccessMode.WRITE;


@Service
public class VideoService {
    private final VideoRepo videoRepo;

    private final Logger logger = LoggerFactory.getLogger(VideoService.class);

    @Value("${data.folder}")
    private String dataFolder;



@Autowired
    public VideoService(VideoRepo videoRepo) {

    this.videoRepo = videoRepo;
   }





    private static VideoMetadataRepr convert(Video video) {
        VideoMetadataRepr repr = new VideoMetadataRepr();

        repr.setId(video.getId());
        repr.setTitle(video.getTitle());
        repr.setPath(video.getPath());//
        repr.setDescription(video.getDescription());
        repr.setContentType(video.getVideoCategory().toString());
        return repr;


    }

    public List<VideoMetadataRepr> findAll() {
        return videoRepo.findAll().stream()
                .map(VideoService::convert)
                .collect(Collectors.toList());
    }

    public Optional<VideoMetadataRepr> findById(Long id) {
        return videoRepo.findById(id)
                .map(VideoService::convert);
    }


    @Transactional
    public void uploadVideo(NewVideoRepr newVideoRepr) {

            User user = new User();

            MultipartFile file = newVideoRepr.getFile();
            String path = dataFolder;
            logger.info("PATH:  .... " + path);
            if (file != null) {
                // SAVE TO DB
                Video video = new Video();
                video.setTitle(newVideoRepr.getTitle());
                video.setDescription(newVideoRepr.getDescription());
                video.setUser(1L);

                video.setAccessStatus(1L);
                video.setViews(0L);
                video.setVideoCategory(1L);
                video.setUploadDate(LocalDateTime.now());
                video.setTime(Time.valueOf("08:33:55"));
                videoRepo.save(video);
                // Save the Video object to the database
                String relativePath = "/"+video.getId().toString();


                Path directory = Paths.get(dataFolder, relativePath);

                logger.info("directory: " + directory);
                if (!Files.exists(directory)) {
                    try {
                        Files.createDirectories(directory);

                        // Generate a unique filename using the current timestamp
                        String fileName = file.getOriginalFilename();

                        // Create a file path for storage
                        Path file2 = directory.resolve(fileName);
                        logger.info("file2: " + file2);

                        // Save the file to the specified path
                        file.transferTo(file2); // toFile() важливо додати тут


                        video.setPath(file2.toString());
                        videoRepo.save(video);

                       // long videoLength = frameGrabberService.generatePreviewPictures(file2);
                      //logger.info(String.valueOf(videoLength));
                    } catch (IOException ex) {
                        logger.error("Error:", ex);
                    }
                }

            }
        }













}

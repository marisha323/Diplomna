package com.example.Diplomna.services;

import com.example.Diplomna.GrabePicture.FrameGrabberService;
import com.example.Diplomna.GrabePicture.NewVideoRepr;
import com.example.Diplomna.GrabePicture.Utils;
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
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//import static java.nio.file.AccessMode.WRITE;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

@Service
public class VideoService {
    private final VideoRepo videoRepo;

    private final Logger logger = LoggerFactory.getLogger(VideoService.class);

    @Value("${data.folder}")
    private String dataFolder;


    private FrameGrabberService frameGrabberService;

@Autowired
    public VideoService(VideoRepo videoRepo, FrameGrabberService frameGrabberService) {

    this.videoRepo = videoRepo;
    this.frameGrabberService = frameGrabberService;
    }





    private static VideoMetadataRepr convert(Video video) {
        VideoMetadataRepr repr = new VideoMetadataRepr();

        repr.setId(video.getId());
        repr.setPreviewUrl("/video/preview/" + video.getId());// відобразити картинку
        repr.setStreamUrl("/video/stream/" + video.getId());// показати відео
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
            user.setId(1L);



            MultipartFile file = newVideoRepr.getFile();
            String path = dataFolder;
            logger.info("PATH:  .... " + path);
            if (file != null) {
                // SAVE TO DB
                Video video = new Video();
                video.setTitle(newVideoRepr.getTitle());
                video.setDescription(newVideoRepr.getDescription());
                video.setUser(1L); // Assuming you have a method to get the user from NewVideoRepr

                video.setAccessStatus(1L); // Assuming you have a method to get the access status
                video.setViews(0L); // Initial views count
                video.setVideoCategory(1L); // Assuming you have a method to get the video category
                video.setUploadDate(LocalDateTime.now());
                video.setTime(Time.valueOf("08:33:55")); // Assuming you have a method to get the time
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

                        // Далі ви можете використовувати file2 для інших операцій, які вам потрібні

                        // Для прикладу, ви можете отримати повний шлях до файлу:

                        video.setPath(file2.toString());
                        videoRepo.save(video);
                        // Також ви можете викликати frameGrabberService.generatePreviewPictures() з file2
                        //long videoLength = frameGrabberService.generatePreviewPictures(file2);

                    } catch (IOException ex) {
                        logger.error("Error:", ex);
                    }
                }

            }
        }


    //GET PICTURE FROM VIDEO
    public Optional<byte[]> getPreviewBytes(Long id) {
        return videoRepo.findById(id)
                .flatMap(vmd -> {
                    Path previewPicturePath = Path.of(dataFolder,
                            vmd.getId().toString(),
                            Utils.removeFileExt(vmd.getTitle()) + ".jpeg");
                    if (!Files.exists(previewPicturePath)) {
                        return Optional.empty();
                    }
                    try {
                        return Optional.of(Files.readAllBytes(previewPicturePath));
                    } catch (IOException ex) {
                        logger.error("", ex);
                        return Optional.empty();
                    }
                });
    }




//    @Transactional
//    public void saveNewVideo(NewVideoRepr newVideoRepr)
//    {
//        Video video = new Video();
//        Path directory = Path.of(dataFolder,video.getId().toString());
//logger.info(newVideoRepr.getTitle());
//
//        video.setTitle(newVideoRepr.getFile().getOriginalFilename());
//        video.setDescription(newVideoRepr.getDescription());
//        video.setPath(directory.toString());
//        video.setUser(null);
//        video.setAccessStatus(null);
//        video.setUploadDate(LocalDateTime.now());
//        video.setVideoCategory(null);
//
////        metadata.setFileName(newVideoRepr.getFile().getOriginalFilename());
////        metadata.setContentType(newVideoRepr.getFile().getContentType());
////        metadata.setFileSize(newVideoRepr.getFile().getSize());
////        metadata.setDescription(newVideoRepr.getDescription());
//
//        videoRepo.save(video);
//
//
//        try{
//            Files.createDirectory(directory);
//            Path file = Path.of(directory.toString(),newVideoRepr.getFile().getOriginalFilename());
//            try(OutputStream out = Files.newOutputStream(file, CREATE)){
//                newVideoRepr.getFile().getInputStream().transferTo(out);
//            }
//            frameGrabberService.generatePreviewPictures(file);
//          //  long videoLength = frameGrabberService.generatePreviewPictures(file);
//
//        }catch (IOException ex)
//        {
//            logger.error("", ex);
//            throw new IllegalStateException();
//        }
//
//    }
//





}

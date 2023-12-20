package com.example.Diplomna.services;

import com.example.Diplomna.classValid.NewVideoRepr;
import com.example.Diplomna.Utils;
import com.example.Diplomna.model.Video;
import com.example.Diplomna.model.VideoMetadataRepr;
import com.example.Diplomna.repo.VideoRepo;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//import static java.nio.file.AccessMode.WRITE;
import static java.nio.file.StandardOpenOption.CREATE;

@Service
public class VideoService {
    private final VideoRepo videoRepo;

    private final Logger logger = LoggerFactory.getLogger(VideoService.class);

    @Value("${data.folder}")
    private String dataFolder;


    private FrameGrabberService frameGrabberService;

@Autowired
    public VideoService(VideoRepo videoRepo) {
        this.videoRepo = videoRepo;
    }


    private static VideoMetadataRepr convert(Video video) {
        VideoMetadataRepr repr = new VideoMetadataRepr();

        repr.setId(video.getId());
        repr.setPreviewUrl("/video/preview/" + video.getId());
        repr.setStreamUrl("/video/stream/" + video.getId());
        repr.setDescription(video.getDescription());
        repr.setContentType(video.getVideoCategory().toString());
        return repr;


    }

    public ResponseEntity<String> uploadVideo(MultipartFile file)
    {
            logger.info("Service File Name: " + file.getOriginalFilename());
        try {
            // Specify the path to the folder for storing files
            String folderPath = "src/main/resources/videos/";
            //MultipartFile file = newVideoRepr.getFile();
            // Ensure that the folder exists; if not, create it
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Generate a unique file name
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            // Create a file path for storage
            Path filePath = Paths.get(folderPath, fileName);

            // Save the file to the specified path
            file.transferTo(filePath);

            // Return the file path as a string
            return new ResponseEntity<>(filePath.toString(), HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to upload the file.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Optional<InputStream> getPreviewInputStream(Long id) {
        return videoRepo.findById(id)
                .flatMap(vmd -> {
                    Path previewPicturePath = Path.of(dataFolder,
                            vmd.getId().toString(),
                            Utils.removeFileExt(vmd.getPath()) + ".jpeg");
                    if (!Files.exists(previewPicturePath)) {
                        return Optional.empty();
                    }
                    try {
                        return Optional.of(Files.newInputStream(previewPicturePath));
                    } catch (IOException ex) {
                        logger.error("", ex);
                        return Optional.empty();
                    }
                });
    }

    public List<VideoMetadataRepr> findAllVideoMetadata() {
        return videoRepo.findAll().stream()
                .map(VideoService::convert)
                .collect(Collectors.toList());
    }

//    public Optional<VideoMetadataRepr>findById(long id)
//    {
//        return videoRepo.findById(id)
//                .map(VideoService::convert);
//    }

    public Optional<VideoMetadataRepr> findById(Long id) {
        return videoRepo.findById(id)
                .map(VideoService::convert);
    }

    @Transactional
    public void saveNewVideo(NewVideoRepr newVideoRepr)
    {
        Video video = new Video();
        Path directory = Path.of(dataFolder,video.getId().toString());
logger.info(newVideoRepr.getTitle());

        video.setTitle(newVideoRepr.getFile().getOriginalFilename());
        video.setDescription(newVideoRepr.getDescription());
        video.setPath(directory.toString());
        video.setUser(null);
        video.setAccessStatus(null);
        video.setUploadDate(LocalDateTime.now());
        video.setVideoCategory(null);

//        metadata.setFileName(newVideoRepr.getFile().getOriginalFilename());
//        metadata.setContentType(newVideoRepr.getFile().getContentType());
//        metadata.setFileSize(newVideoRepr.getFile().getSize());
//        metadata.setDescription(newVideoRepr.getDescription());

        videoRepo.save(video);


        try{
            Files.createDirectory(directory);
            Path file = Path.of(directory.toString(),newVideoRepr.getFile().getOriginalFilename());
            try(OutputStream out = Files.newOutputStream(file, CREATE)){
                newVideoRepr.getFile().getInputStream().transferTo(out);
            }
            frameGrabberService.generatePreviewPictures(file);
          //  long videoLength = frameGrabberService.generatePreviewPictures(file);

        }catch (IOException ex)
        {
            logger.error("", ex);
            throw new IllegalStateException();
        }

    }






}

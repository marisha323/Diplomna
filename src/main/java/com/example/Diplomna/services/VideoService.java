package com.example.Diplomna.services;


import com.example.Diplomna.GrabePicture.NewVideoRepr;
import com.example.Diplomna.classValid.CrmHelper;
import com.example.Diplomna.enums.NotFoundException;
import com.example.Diplomna.model.User;
import com.example.Diplomna.model.Video;
import com.example.Diplomna.GrabePicture.VideoMetadataRepr;
import com.example.Diplomna.repo.UserRepo;
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
import java.util.stream.Stream;

//import static java.nio.file.AccessMode.WRITE;


@Service
public class VideoService {
    private final VideoRepo videoRepo;
    private final Logger logger = LoggerFactory.getLogger(VideoService.class);
    @Value("${data.folder}")
    private String dataFolder;
    private UserRepo userRepo;
    @Autowired
    private UserService userService;

    @Autowired
    public VideoService(VideoRepo videoRepo, UserRepo userRepo) {
        this.userRepo = userRepo;
        this.videoRepo = videoRepo;
    }
    private static VideoMetadataRepr convert(Video video, User user) {
        VideoMetadataRepr repr = new VideoMetadataRepr();

        repr.setId(video.getId());
        repr.setTitle(video.getTitle());
        repr.setPath(video.getPath());//
        repr.setDescription(video.getDescription());
        repr.setContentType(video.getVideoCategory().toString());
        repr.setAccessStatus(video.getAccessStatus().toString());
        repr.setUserId(video.getUser());
        repr.setUsername(user.getUserName());
        repr.setPathAVA(user.getPhotoUrl());
        return repr;


    }
    public List<VideoMetadataRepr> findAll(int accessStatus) {
        return videoRepo.findAll().stream()
                .filter(video -> video.getAccessStatus() == accessStatus)
                .flatMap(video -> userService.findUserById(video.getUser())
                        .map(user -> Stream.of(convert(video, user)))
                        .orElseGet(Stream::empty))
                .collect(Collectors.toList());
    }
    public Optional<VideoMetadataRepr> findById(Long id) {
        return videoRepo.findById(id)
                .flatMap(video -> userService.findUserById(video.getUser())
                        .map(user -> convert(video, user)));
    }
    @Transactional
    public void uploadVideo(String authorizationHeader, NewVideoRepr newVideoRepr) {
        CrmHelper crmHelper = new CrmHelper(userRepo);
        Long userId = crmHelper.userId(authorizationHeader);
        MultipartFile file = newVideoRepr.getFile();
        String path = dataFolder;
        logger.info("PATH:  .... " + path);
        if (userId != null&&file != null) {
                // SAVE TO DB
                Video video = new Video();
                video.setTitle(newVideoRepr.getTitle());
                video.setDescription(newVideoRepr.getDescription());
                video.setUser(userId);
                video.setAccessStatus(newVideoRepr.getAccessStatusId());
                video.setViews(0L);
                video.setVideoCategory(newVideoRepr.getCategoryId());
                video.setUploadDate(LocalDateTime.now());
                video.setTime(Time.valueOf("08:33:55"));
                videoRepo.save(video);
                String relativePath = "/" + video.getId().toString();

                Path directory = Paths.get(dataFolder, relativePath);
                logger.info("directory: " + directory);
                if (!Files.exists(directory)) {
                    try {
                        Files.createDirectories(directory);
                        String fileName = file.getOriginalFilename();
                        Path file2 = directory.resolve(fileName);
                        logger.info("file2: " + file2);
                        file.transferTo(file2);
                        video.setPath(file2.toString());
                        videoRepo.save(video);
                    } catch (IOException ex) {
                        logger.error("Error:", ex);
                    }
                }
        }
    }

    public byte[] downloadVideo(String name) throws IOException {
        Video video = videoRepo.findByTitle(name).orElseThrow(() -> new NotFoundException());

        return Files.readAllBytes(new File(video.getPath()).toPath());

    }



    public List<Video> getVideosByCategoryId(Long videoCategoryId) {
        return videoRepo.findByVideoCategory_Id(videoCategoryId);
    }
    public List<Video> searchByTitle(String title) {
        return videoRepo.findByTitleContaining(title);
    }
    public boolean existsById(Long id) {
        return videoRepo.existsById(id);
    }
}

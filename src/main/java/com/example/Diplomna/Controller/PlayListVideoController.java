package com.example.Diplomna.Controller;

import com.example.Diplomna.classValid.PlayListVideoCrm;
import com.example.Diplomna.classValid.VideoDTO;
import com.example.Diplomna.enums.NotFoundException;
import com.example.Diplomna.model.PlayListVideo;
import com.example.Diplomna.model.User;
import com.example.Diplomna.model.Video;
import com.example.Diplomna.repo.PlayListVideoRepo;
import com.example.Diplomna.repo.UserRepo;
import com.example.Diplomna.repo.VideoRepo;
import com.example.Diplomna.services.PlayListVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/list_video")
public class PlayListVideoController {
    @Autowired
    private PlayListVideoService playListVideoService;

    @Autowired
    private PlayListVideoRepo playListVideoRepo;
    @Autowired
    private VideoRepo videoRepo;
    @Autowired
    private static UserRepo userRepo;

    public PlayListVideoController(PlayListVideoRepo playListVideoRepo, VideoRepo videoRepo, UserRepo userRepo) {
        this.playListVideoRepo = playListVideoRepo;
        this.videoRepo = videoRepo;
        this.userRepo = userRepo;
    }

    @PostMapping("/add-video-playlist")
    public ResponseEntity<String> addVideoPlayList(PlayListVideoCrm playListVideoCrm) {
        try {
            playListVideoService.addVideoPlayList(playListVideoCrm);
            return ResponseEntity.ok("В плейлист успішно додалося відео");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("В плейлист не додалося відео");
        }
    }

    @GetMapping("/all-video-playlist")
    public ResponseEntity<?> allVideoPlayList(Long playListId) {
        List<PlayListVideo> playListVideos = playListVideoRepo.findAllByPlayListId(playListId);
        List<Video> videos = new ArrayList<>();

        for (PlayListVideo playListVideo : playListVideos) {
            Optional<Video> videoOptional = videoRepo.findById(playListVideo.getVideo());
            videoOptional.ifPresent(videos::add);
        }

        return ResponseEntity.ok(videos);
    }

    @GetMapping("/all-video-playlist-info")
    public ResponseEntity<?> allVideoPlayList2(Long playListId) {
        List<PlayListVideo> playListVideos = playListVideoRepo.findAllByPlayListId(playListId);
        List<VideoDTO> videoDTOList = new ArrayList<>();

        for (PlayListVideo playListVideo : playListVideos) {
            Optional<Video> videoOptional = videoRepo.findById(playListVideo.getVideo());
            videoOptional.ifPresent(video -> {
                VideoDTO videoDTO = new VideoDTO();
                videoDTO.setId(video.getId());
                videoDTO.setTitle(video.getTitle());
                videoDTO.setDescription(video.getDescription());
                videoDTO.setPath(video.getPath());

                Optional<User> userOptional = userRepo.findById(video.getUser());
                userOptional.ifPresent(user -> {
                    videoDTO.setUserName(user.getUserName());
                    videoDTO.setPhotoUrl(user.getPhotoUrl());
                    // Завантаження фото користувача
                    try {
                        byte[] photoBytes = downloadAvaUser(user.getId());
                        videoDTO.setPhotoBete(photoBytes);
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to download user photo", e);
                    }
                });

                videoDTOList.add(videoDTO);
            });
        }

        return ResponseEntity.ok(videoDTOList);
    }

    public static byte[] downloadAvaUser(Long id) throws IOException {
        User user = userRepo.findById(id).orElseThrow(() -> new NotFoundException());

        return Files.readAllBytes(new File(user.getPhotoUrl()).toPath());
    }



}

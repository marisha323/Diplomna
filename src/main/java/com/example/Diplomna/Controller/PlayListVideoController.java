package com.example.Diplomna.Controller;

import com.example.Diplomna.classValid.PlayListVideoCrm;
import com.example.Diplomna.model.PlayListVideo;
import com.example.Diplomna.model.Video;
import com.example.Diplomna.repo.PlayListVideoRepo;
import com.example.Diplomna.repo.VideoRepo;
import com.example.Diplomna.services.PlayListVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public PlayListVideoController(PlayListVideoRepo playListVideoRepo,VideoRepo videoRepo) {
        this.playListVideoRepo = playListVideoRepo;
        this.videoRepo=videoRepo;
    }

    @PostMapping("/add-video-playlist")
    public ResponseEntity<String> addVideoPlayList(PlayListVideoCrm playListVideoCrm) {
        try {
            playListVideoService.addVideoPlayList(playListVideoCrm);
            return ResponseEntity.ok("В плейлист успішно додалося відео");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("В плейлист не додалося відео");
        }
    }
    @GetMapping("/all-video-playlist")
    public ResponseEntity<?> allVideoPlayList(Long playListId){
        List<PlayListVideo> playListVideos = playListVideoRepo.findAllByPlayListId(playListId);
        List<Video> videos = new ArrayList<>();

        for (PlayListVideo playListVideo : playListVideos) {
            Optional<Video> videoOptional = videoRepo.findById(playListVideo.getVideo());
            videoOptional.ifPresent(videos::add);
        }

        return ResponseEntity.ok(videos);
    }

    @GetMapping("/all-video-playlist-info")
    public ResponseEntity<?> allVideoPlayListInfo(Long playListId){
        try {
            return ResponseEntity.ok(playListVideoService.getVideosFromPlaylist(playListId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error retrieving playlist information");
        }
    }


}

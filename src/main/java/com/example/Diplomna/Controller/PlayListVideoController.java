package com.example.Diplomna.Controller;

import com.example.Diplomna.classValid.PlayListVideoCrm;
import com.example.Diplomna.services.PlayListVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/list_video")
public class PlayListVideoController {
    @Autowired
    private PlayListVideoService playListVideoService;

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
    public ResponseEntity<?> allVideoPlayList(){

        return ResponseEntity.ok("В плейлист успішно додалося відео");
    }
}

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
    public ResponseEntity<Boolean> addVideoPlayList(@RequestBody PlayListVideoCrm playListVideoCrm) {
        try {
            boolean status = playListVideoService.addVideoPlayList(playListVideoCrm);
            return ResponseEntity.ok(status);
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }
    @GetMapping("/all-video-playlist")
    public ResponseEntity<?> allVideoPlayList(){

        return ResponseEntity.ok("В плейлист успішно додалося відео");
    }
}

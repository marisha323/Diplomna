package com.example.Diplomna.Controller;


import com.example.Diplomna.classValid.Comment_Crm;
import com.example.Diplomna.classValid.PlaylistCrm;
import com.example.Diplomna.model.PlayList;
import com.example.Diplomna.repo.PlayListRepo;
import com.example.Diplomna.repo.UserRepo;
import com.example.Diplomna.services.PlayListService;
import jdk.jshell.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlist")
public class PlayListController {

    private PlayListRepo playListRepo;

    @Autowired
    private PlayListService playListService;

    private UserRepo userRepo;

    public PlayListController(PlayListRepo playListRepo, UserRepo userRepo) {
        this.playListRepo = playListRepo;
        this.userRepo = userRepo;
    }

    @PostMapping("/create-playlist")
    public ResponseEntity<String> playList(@RequestHeader("Authorization") String authorizationHeader, PlaylistCrm playlistCrm) {

        try {
            playListService.addPlayList(authorizationHeader, playlistCrm);
            return ResponseEntity.ok("Плейлист успішно створився");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Плейлист не створився");
        }
    }

    @GetMapping("/user-playlists")
    public ResponseEntity<List<PlayList>> getUserPlayLists(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            List<PlayList> userPlaylists = playListService.getPlayListsByUserId(authorizationHeader);
            return ResponseEntity.ok(userPlaylists);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }



}

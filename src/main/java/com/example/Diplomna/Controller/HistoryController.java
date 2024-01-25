package com.example.Diplomna.Controller;

import com.example.Diplomna.model.History;
import com.example.Diplomna.repo.UserRepo;
import com.example.Diplomna.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    private HistoryService historyService;
    private UserRepo userRepo;

    public HistoryController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/all-history-video")
    public ResponseEntity<List<History>> allVideoHistory(@RequestHeader("Authorization") String authorizationHeader){
        try {
            List<History> userPlaylists = historyService.getHistoryListByUserId(authorizationHeader);
            return ResponseEntity.ok(userPlaylists);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}

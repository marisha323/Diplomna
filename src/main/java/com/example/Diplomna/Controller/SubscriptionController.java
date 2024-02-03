package com.example.Diplomna.Controller;

import com.example.Diplomna.classValid.CrmHelper;
import com.example.Diplomna.classValid.SubscriptionCrm;
import com.example.Diplomna.repo.UserRepo;
import com.example.Diplomna.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subs")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;
    private UserRepo userRepo;

    public SubscriptionController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping("/add-sub")
    public ResponseEntity<?> addSubscription(@RequestHeader("Authorization") String authorizationHeader, SubscriptionCrm subscriptionCrm) {
        try {
            subscriptionService.addSubscription(authorizationHeader, subscriptionCrm);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/get-video-sub-user")
    public ResponseEntity<?> getVideoSubUser(@RequestHeader("Authorization") String authorizationHeader){

        return subscriptionService.getVideoSubUser(authorizationHeader);
    }
}

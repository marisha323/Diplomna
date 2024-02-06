package com.example.Diplomna.Controller;

import com.example.Diplomna.classValid.CrmHelper;
import com.example.Diplomna.classValid.SubscriptionCrm;
import com.example.Diplomna.model.User;
import com.example.Diplomna.repo.UserRepo;
import com.example.Diplomna.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subs")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
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


    @GetMapping("/count_subscriptionMyChannel")
    public long countVideoOfMyChannel(@RequestHeader("Authorization") String authorizationHeader) {
        CrmHelper crmHelper = new CrmHelper(userRepo);
        Long userId = crmHelper.userId(authorizationHeader);
        return subscriptionService.countSubscription(userId);
    }

    @GetMapping("/subscribed-users")
    public ResponseEntity<List<User>> getSubscribedUsers(@RequestHeader("Authorization") String authorizationHeader) {

        List<User> subscribedUsers = subscriptionService.getSubscribedUsers(authorizationHeader);
        return ResponseEntity.ok(subscribedUsers);
    }
}

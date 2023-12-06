package com.example.Diplomna.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/client")
public class ClientController {
    @PostMapping("/login")
    public ResponseEntity<Object> login(String tokenString) {
        System.out.println(tokenString);
        return new ResponseEntity<>(tokenString, HttpStatus.OK);
    }
}

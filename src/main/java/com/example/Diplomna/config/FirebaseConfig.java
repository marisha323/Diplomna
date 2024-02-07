package com.example.Diplomna.config;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {
@Bean
    public FirebaseApp firebaseApp() throws IOException{
        FileInputStream serviceAccount =
                new FileInputStream("src/config/login-c47f8-firebase-adminsdk-vxhps-60abef6998.json");

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket("login-c47f8.appspot.com")
                .build();

        return FirebaseApp.initializeApp(options);
    }


}
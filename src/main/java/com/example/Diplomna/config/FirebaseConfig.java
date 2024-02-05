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
                new FileInputStream("D:\\JAVAProject\\Diplomna2\\src\\config\\login-c47f8-firebase-adminsdk-vxhps-963ee9cc2e.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        return FirebaseApp.initializeApp(options);
    }


}
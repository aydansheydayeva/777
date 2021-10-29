package com.example7.demo7.firebase;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import org.springframework.stereotype.Service;

@Service
public class FirebaseInitialization {

    @PostConstruct
    public void initialization() throws IOException{

        FirebaseOptions options = new FirebaseOptions.Builder()
        .setCredentials(GoogleCredentials.fromStream(new ByteArrayInputStream(System.getenv("FIREBASE_CREDENTIALS").getBytes())))
        .setDatabaseUrl("https://demo7.firebaseio.com")
        .build();

        FirebaseApp.initializeApp(options);

    }
    
}

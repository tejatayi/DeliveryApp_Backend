package com.deliveryapplication.Fusion.Firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.FirebaseOptions.Builder;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class FirebaseConfig {

    private static final Logger logger = LoggerFactory.getLogger(FirebaseConfig.class);
    @PostConstruct
    public void initializeFirebase() throws IOException {
        logger.info("InitializeFirebase method started");
        FileInputStream serviceAccount = new FileInputStream("/Users/saisivatejatayi/Documents/Fusion/src/main/java/com/deliveryapplication/Fusion/Firebase/deliveryapplication_firebase_sdk.json");

        logger.info("ServiceAccount: {}", serviceAccount);


        FirebaseOptions options = new Builder().setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();
        logger.info("FirebaseOptions: {}", options);

        System.out.println("Main Content :" +options.getDatabaseUrl()+options.getProjectId());

        if(FirebaseApp.getApps().isEmpty())
        {
            FirebaseApp.initializeApp(options);
        }
    }
}

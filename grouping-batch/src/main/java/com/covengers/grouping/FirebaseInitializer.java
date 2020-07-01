package com.covengers.grouping;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.slf4j.Slf4j;
import org.nd4j.common.io.ClassPathResource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class FirebaseInitializer {

    private static InputStream serviceAccount = null;
    private static FirebaseOptions options = null;

    public static void initFirebase() {
        try {
            ClassPathResource resource = new ClassPathResource("source-data.json");
            serviceAccount = resource.getInputStream();
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (FileNotFoundException e) {
            log.info("Firebase ServiceAccountKey FileNotFoundException" + e.getMessage());
        } catch (IOException e) {
            log.info("FirebaseOptions IOException" + e.getMessage());
        }

    }
}

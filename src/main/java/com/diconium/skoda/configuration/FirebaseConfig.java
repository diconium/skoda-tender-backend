package com.diconium.skoda.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirebaseConfig {
    @Value("${FIREBASE_CREDENTIALS}")
    private String firebaseCredentials;

    @Bean
    public FirebaseApp createFirebaseApp() throws IOException {
        final ByteArrayInputStream serviceAccount = new ByteArrayInputStream(firebaseCredentials.getBytes());
        final FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        if (!FirebaseApp.getApps().isEmpty()) {
            for (final FirebaseApp app : FirebaseApp.getApps()) {
                if (app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)) {
                    return app;
                }
            }
        }

        return FirebaseApp.initializeApp(options);
    }
}

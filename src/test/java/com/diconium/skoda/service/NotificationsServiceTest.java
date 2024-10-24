package com.diconium.skoda.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import com.diconium.skoda.exception.FirebaseException;
import com.diconium.skoda.model.dto.NotificationDto;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NotificationsServiceTest {
    @Mock
    private FirebaseApp firebaseApp;

    @Mock
    private FirebaseMessaging firebaseMessaging;

    private static MockedStatic<FirebaseMessaging> messagingMockedStatic;

    @InjectMocks
    private NotificationsServiceImpl notificationsService;

    @BeforeEach
    void setUp() {
        messagingMockedStatic = Mockito.mockStatic(FirebaseMessaging.class);
        Mockito.when(FirebaseMessaging.getInstance(firebaseApp)).thenReturn(firebaseMessaging);
        Mockito.when(FirebaseMessaging.getInstance()).thenReturn(firebaseMessaging);
    }

    @AfterEach
    public void close() {
        messagingMockedStatic.close();
    }

    @Test
    void sendNotification_shouldSendNotificationSuccessfully() throws Exception {
        var notificationDto = new NotificationDto(1, "1HGCM82633A123456", "Test Title", "Test Message");

        notificationsService.sendNotification(notificationDto);

        verify(firebaseMessaging).send(any(Message.class));
    }

    @Test
    void sendNotification_shouldThrowFirebaseExceptionOnFailure() throws Exception {
        var notificationDto = new NotificationDto(1, "1HGCM82633A123456", "Test Title", "Test Message");
        doThrow(new RuntimeException("Firebase error")).when(firebaseMessaging).send(any(Message.class));

        assertThrows(FirebaseException.class, () -> notificationsService.sendNotification(notificationDto));
    }

    @Test
    void sendNotification_shouldLogErrorOnFailure() throws Exception {
        var notificationDto = new NotificationDto(1, "1HGCM82633A123456", "Test Title", "Test Message");
        doThrow(new RuntimeException("Firebase error")).when(firebaseMessaging).send(any(Message.class));

        try {
            notificationsService.sendNotification(notificationDto);
        } catch (FirebaseException ignored) {
        }

        verify(firebaseMessaging).send(any(Message.class));
    }
}

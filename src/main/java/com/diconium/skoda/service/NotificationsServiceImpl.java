package com.diconium.skoda.service;

import com.diconium.skoda.exception.FirebaseException;
import com.diconium.skoda.model.dto.NotificationDto;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationsServiceImpl implements NotificationsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationsServiceImpl.class);

    public NotificationsServiceImpl(final FirebaseApp firebaseApp) {
        FirebaseMessaging.getInstance(firebaseApp);
    }

    /** {@inheritDoc} */
    public void sendNotification(final NotificationDto notificationDto) {
        final Message firebaseMessage = Message.builder()
                .putData("vin", notificationDto.vin())
                .putData("title", notificationDto.title())
                .putData("message", notificationDto.message())
                .setTopic(notificationDto.vin())
                .build();

        try {
            FirebaseMessaging.getInstance().send(firebaseMessage);
        } catch (final Exception e) {
            LOGGER.error(
                    "Error while sending notification for vin: {}, title: {}, message: {}",
                    notificationDto.vin(),
                    notificationDto.title(),
                    notificationDto.message(),
                    e);

            throw new FirebaseException(notificationDto.vin(), notificationDto.title(), notificationDto.message());
        }
    }
}

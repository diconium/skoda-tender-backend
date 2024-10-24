package com.diconium.skoda.service;

import com.diconium.skoda.exception.FirebaseException;
import com.diconium.skoda.model.dto.NotificationDto;

public interface NotificationsService {
    /**
     * Sends a notification using the provided {@link NotificationDto}.
     *
     * @param notificationDto the notification data transfer object containing the details of the notification to be
     *     sent
     * @throws FirebaseException if there is an error while sending the notification
     */
    void sendNotification(NotificationDto notificationDto);
}

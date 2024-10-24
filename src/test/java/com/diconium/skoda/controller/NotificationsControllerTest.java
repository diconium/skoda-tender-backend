package com.diconium.skoda.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.diconium.skoda.exception.FirebaseException;
import com.diconium.skoda.model.dto.NotificationDto;
import com.diconium.skoda.service.NotificationsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(NotificationsController.class)
public class NotificationsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationsService notificationsService;

    @Test
    void testSendNotification() throws Exception {
        var jsonContent =
                "{ \"id\": 1, \"vin\": \"VIN123\", \"title\": \"Test Title\", \"message\": \"Test Message\" }";

        mockMvc.perform(MockMvcRequestBuilders.post("/notifications/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    void testSendNotificationInternalError() throws Exception {
        var notificationDto = new NotificationDto(1, "VIN123", "Test Title", "Test Message");
        var jsonContent =
                "{ \"id\": \"1\", \"vin\": \"VIN123\", \"title\": \"Test Title\", \"message\": \"Test Message\" }";

        Mockito.doThrow(new RuntimeException("Internal server error"))
                .when(notificationsService)
                .sendNotification(notificationDto);

        mockMvc.perform(post("/notifications/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void testSendNotificationFirebaseException() throws Exception {
        var notificationDto = new NotificationDto(1, "VIN123", "Test Title", "Test Message");
        var jsonContent =
                "{ \"id\": 1, \"vin\": \"VIN123\", \"title\": \"Test Title\", \"message\": \"Test Message\" }";

        Mockito.doThrow(new FirebaseException("test", "test", "test"))
                .when(notificationsService)
                .sendNotification(notificationDto);

        mockMvc.perform(post("/notifications/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isInternalServerError());
    }
}

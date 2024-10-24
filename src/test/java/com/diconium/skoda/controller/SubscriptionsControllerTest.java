package com.diconium.skoda.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.diconium.skoda.exception.VinNotFoundException;
import com.diconium.skoda.model.dto.*;
import com.diconium.skoda.service.SubscriptionsService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SubscriptionsController.class)
class SubscriptionsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubscriptionsService subscriptionsService;

    private SubscriptionsDto mockSubscriptionsDto;

    @BeforeEach
    void setUp() {
        var carDto = new CarDto("1HGCM82633A123456", "Škoda", "Octavia", 2020);
        var userDto = new UserDto(1, "test@example.com", "testuser");
        var productDto = new ProductDto(1, "Product Name", "Product Description", "product.png");
        var subscriptionDto = new SubscriptionDto(
                1,
                "Service Name",
                "Service Description",
                "service.png",
                12,
                99.99f,
                List.of(productDto),
                "Active",
                "2021-01-01",
                "2022-01-01");

        mockSubscriptionsDto = new SubscriptionsDto(userDto, carDto, List.of(subscriptionDto));
    }

    @Test
    void testGetSubscriptionsForVin_success() throws Exception {
        Mockito.when(subscriptionsService.getAllServicesForVin(anyString())).thenReturn(mockSubscriptionsDto);

        mockMvc.perform(get("/subscriptions/1HGCM82633A123456/status").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.car.vin").value("1HGCM82633A123456"));
    }

    @Test
    void testGetSubscriptionsForVin_vinNotFound() throws Exception {
        Mockito.when(subscriptionsService.getAllServicesForVin(anyString()))
                .thenThrow(new VinNotFoundException("ABC123"));

        mockMvc.perform(get("/subscriptions/ABC123/status").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("No car found for VIN: ABC123"))
                .andExpect(jsonPath("$.code").value(404));
    }
}

package com.diconium.skoda.service;

import com.diconium.skoda.exception.VinNotFoundException;
import com.diconium.skoda.model.entity.*;
import com.diconium.skoda.repository.CarConnectServiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubscriptionsServiceTest {

    @Mock
    private CarConnectServiceRepository carConnectServiceRepository;

    @InjectMocks
    private SubscriptionsServiceImpl subscriptionsService;

    private CarConnectService carConnectService;

    @BeforeEach
    void setUp() {
        var user = new User();
        user.setId(1);
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("testpassword");
        user.setName("Test User");
        user.setCreatedAt(LocalDate.now().atStartOfDay());

        var car = new Car();
        car.setVin("1HGCM82633A123456");
        car.setBrand("Škoda");
        car.setModel("Octavia");
        car.setYear(2020);
        car.setUser(user);

        var product = new Product();
        product.setId(1);
        product.setName("Product Name");
        product.setDescription("Product Description");
        product.setImage("product.png");

        var connectService = new ConnectService();
        connectService.setId(1);
        connectService.setName("Service Name");
        connectService.setDescription("Service Description");
        connectService.setImage("service.png");
        connectService.setContractLength(12);
        connectService.setPrice(BigDecimal.valueOf(99.99));
        connectService.setProducts(Set.of(product));

        var carConnectServiceId = new CarConnectServiceId();
        carConnectServiceId.setCarVin("1HGCM82633A123456");
        carConnectServiceId.setConnectServiceId(1);

        carConnectService = new CarConnectService();
        carConnectService.setId(carConnectServiceId);
        carConnectService.setCar(car);
        carConnectService.setConnectService(connectService);
        carConnectService.setStatus("Active");
        carConnectService.setStartDate(LocalDate.now().atStartOfDay());
        carConnectService.setEndDate(LocalDate.now().plusMonths(12).atStartOfDay());
    }

    @Test
    void getAllServicesForVin_shouldReturnSubscriptionsDto_whenVinExists() {
        when(carConnectServiceRepository.findByCarVin("1HGCM82633A123456")).thenReturn(List.of(carConnectService));

        var result = subscriptionsService.getAllServicesForVin("1HGCM82633A123456");

        assertNotNull(result);
        assertEquals("1HGCM82633A123456", result.car().vin());
        assertEquals("test@example.com", result.user().email());
        assertEquals(1, result.subscriptions().size());
        assertEquals("Service Name", result.subscriptions().get(0).name());
    }

    @Test
    void getAllServicesForVin_shouldThrowVinNotFoundException_whenVinDoesNotExist() {
        when(carConnectServiceRepository.findByCarVin("1HGCM82633A123456")).thenReturn(Collections.emptyList());

        assertThrows(VinNotFoundException.class, () -> subscriptionsService.getAllServicesForVin("1HGCM82633A123456"));
    }

    @Test
    void getAllServicesForVin_shouldThrowInternalServerError_whenUnexpectedExceptionOccurs() {
        when(carConnectServiceRepository.findByCarVin("1HGCM82633A123456"))
                .thenThrow(new RuntimeException("Unexpected error"));

        var exception = assertThrows(
                RuntimeException.class, () -> subscriptionsService.getAllServicesForVin("1HGCM82633A123456"));
        assertEquals("Unexpected error", exception.getMessage());
    }

    @Test
    void getAllServicesForVin_shouldReturnFormattedDates() {
        when(carConnectServiceRepository.findByCarVin("1HGCM82633A123456")).thenReturn(List.of(carConnectService));

        var result = subscriptionsService.getAllServicesForVin("1HGCM82633A123456");

        assertNotNull(result);
        assertEquals(1, result.subscriptions().size());

        var subscription = result.subscriptions().get(0);
        var dateTimePattern = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}";

        assertTrue(subscription.startDate().matches(dateTimePattern), "Start date format is incorrect");
        assertTrue(subscription.endDate().matches(dateTimePattern), "End date format is incorrect");
    }
}

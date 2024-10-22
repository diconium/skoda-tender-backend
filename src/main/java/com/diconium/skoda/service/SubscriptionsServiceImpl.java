package com.diconium.skoda.service;

import com.diconium.skoda.model.dto.*;
import com.diconium.skoda.model.entity.Car;
import com.diconium.skoda.model.entity.CarConnectService;
import com.diconium.skoda.model.entity.Product;
import com.diconium.skoda.model.entity.User;
import com.diconium.skoda.repository.CarConnectServiceRepository;

import java.util.Comparator;
import java.util.List;

@org.springframework.stereotype.Service
public class SubscriptionsServiceImpl implements SubscriptionsService {

    private final CarConnectServiceRepository carConnectServiceRepository;

    public SubscriptionsServiceImpl(final CarConnectServiceRepository carConnectServiceRepository) {
        this.carConnectServiceRepository = carConnectServiceRepository;
    }

    @Override
    public SubscriptionsDto getAllServicesForVin(final String vin) {
        // Fetch all CarConnectService entities by VIN
        final List<CarConnectService> carConnectServices = carConnectServiceRepository.findByCarVin(vin);
        final Car car = carConnectServices.getFirst().getCar();
        final User user = carConnectServices.getFirst().getCar().getUser();

        // Map CarConnectService entities to SubscriptionDto
        final List<SubscriptionDto> subscriptionDtos = carConnectServices.stream()
                .sorted(Comparator.comparing(s -> s.getId().getConnectServiceId()))
                .map(carConnectService -> new SubscriptionDto(
                        carConnectService.getId().getConnectServiceId(),
                        carConnectService.getService().getName(),
                        carConnectService.getService().getDescription(),
                        carConnectService.getService().getImage(),
                        carConnectService.getService().getContractLength(),
                        carConnectService.getService().getPrice().floatValue(),
                        carConnectService.getService().getProducts().stream()
                                .sorted(Comparator.comparing(Product::getId))
                                .map(product -> new ProductsDto(
                                        product.getId(),
                                        product.getName(),
                                        product.getDescription(),
                                        product.getImage()
                                ))
                                .toList(),
                        carConnectService.getStatus(),
                        carConnectService.getStartDate().toString(),
                        carConnectService.getEndDate().toString()
                ))
                .toList();

        // Create and return SubscriptionsDto
        return new SubscriptionsDto(
                new UserDto(
                        user.getId(),
                        user.getEmail(),
                        user.getUsername()
                ),
                new CarDto(
                        car.getVin(),
                        car.getBrand(),
                        car.getModel(),
                        car.getYear()
                ),
                subscriptionDtos
        );
    }
}

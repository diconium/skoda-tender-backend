package com.diconium.skoda.service;

import com.diconium.skoda.exception.VinNotFoundException;
import com.diconium.skoda.model.dto.*;
import com.diconium.skoda.model.entity.Car;
import com.diconium.skoda.model.entity.CarConnectService;
import com.diconium.skoda.model.entity.Product;
import com.diconium.skoda.model.entity.User;
import com.diconium.skoda.repository.CarConnectServiceRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class SubscriptionsServiceImpl implements SubscriptionsService {

    private final CarConnectServiceRepository carConnectServiceRepository;

    public SubscriptionsServiceImpl(final CarConnectServiceRepository carConnectServiceRepository) {
        this.carConnectServiceRepository = carConnectServiceRepository;
    }

    @Override
    public SubscriptionsDto getAllServicesForVin(final String vin) {
        final List<CarConnectService> carConnectServices = carConnectServiceRepository.findByCarVin(vin);

        if (carConnectServices.isEmpty()) {
            throw new VinNotFoundException(vin);
        }

        final Car car = carConnectServices.getFirst().getCar();
        final User user = carConnectServices.getFirst().getCar().getUser();

        final List<SubscriptionDto> subscriptionDtos = carConnectServices.stream()
                .sorted(Comparator.comparing(s -> s.getId().getConnectServiceId()))
                .map(carConnectService -> new SubscriptionDto(
                        carConnectService.getId().getConnectServiceId(),
                        carConnectService.getConnectService().getName(),
                        carConnectService.getConnectService().getDescription(),
                        carConnectService.getConnectService().getImage(),
                        carConnectService.getConnectService().getContractLength(),
                        carConnectService.getConnectService().getPrice().floatValue(),
                        carConnectService.getConnectService().getProducts().stream()
                                .sorted(Comparator.comparing(Product::getId))
                                .map(product -> new ProductsDto(
                                        product.getId(),
                                        product.getName(),
                                        product.getDescription(),
                                        product.getImage()))
                                .toList(),
                        carConnectService.getStatus(),
                        carConnectService.getStartDate().toString(),
                        carConnectService.getEndDate().toString()))
                .toList();

        return new SubscriptionsDto(
                new UserDto(user.getId(), user.getEmail(), user.getUsername()),
                new CarDto(car.getVin(), car.getBrand(), car.getModel(), car.getYear()),
                subscriptionDtos);
    }
}

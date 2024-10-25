package com.diconium.skoda.service;

import com.diconium.skoda.exception.VinNotFoundException;
import com.diconium.skoda.model.dto.*;
import com.diconium.skoda.model.entity.Car;
import com.diconium.skoda.model.entity.CarConnectService;
import com.diconium.skoda.model.entity.Product;
import com.diconium.skoda.model.entity.User;
import com.diconium.skoda.repository.CarConnectServiceRepository;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionsServiceImpl implements SubscriptionsService {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    private final CarConnectServiceRepository carConnectServiceRepository;

    public SubscriptionsServiceImpl(final CarConnectServiceRepository carConnectServiceRepository) {
        this.carConnectServiceRepository = carConnectServiceRepository;
    }

    /** {@inheritDoc} */
    @Override
    public SubscriptionsDto getAllServicesForVin(final String vin) {
        final List<CarConnectService> carConnectServices = carConnectServiceRepository.findByCarVin(vin);

        if (carConnectServices.isEmpty()) {
            throw new VinNotFoundException(vin);
        }

        final Car car = carConnectServices.get(0).getCar();
        final User user = carConnectServices.get(0).getCar().getUser();

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
                                .map(product -> new ProductDto(
                                        product.getId(),
                                        product.getName(),
                                        product.getDescription(),
                                        product.getImage()))
                                .toList(),
                        carConnectService.getStatus(),
                        carConnectService.getStartDate().format(DATE_TIME_FORMATTER),
                        carConnectService.getEndDate().format(DATE_TIME_FORMATTER)))
                .toList();

        return new SubscriptionsDto(
                new UserDto(user.getId(), user.getEmail(), user.getUsername()),
                new CarDto(car.getVin(), car.getBrand(), car.getModel(), car.getYear()),
                subscriptionDtos);
    }
}

package com.diconium.skoda.service;

import com.diconium.skoda.model.dto.SubscriptionsDto;

public interface SubscriptionsService {

    /**
     * Retrieves all services for a given car VIN.
     *
     * @param vin the car's VIN number
     * @return a list of services associated with the car
     */
    SubscriptionsDto getAllServicesForVin(String vin);
}

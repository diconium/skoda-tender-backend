package com.diconium.skoda.service;

import com.diconium.skoda.exception.VinNotFoundException;
import com.diconium.skoda.model.dto.SubscriptionsDto;

public interface SubscriptionsService {

    /**
     * Retrieves all subscription services for a given car VIN.
     *
     * <p>Fetches all subscription services associated with the specified Vehicle Identification Number (VIN). Returns
     * detailed information about the user, car, and the list of subscription services.
     *
     * @param vin the car's VIN number
     * @return a SubscriptionsDto containing user, car, and subscription services information
     * @throws VinNotFoundException if the VIN is not found
     */
    SubscriptionsDto getAllServicesForVin(String vin);
}

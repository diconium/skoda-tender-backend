package com.diconium.skoda.repository;

import com.diconium.skoda.model.entity.CarConnectService;
import com.diconium.skoda.model.entity.CarConnectServiceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarConnectServiceRepository extends JpaRepository<CarConnectService, CarConnectServiceId> {
    // Custom query method to find all services by car VIN
    List<CarConnectService> findByCarVin(String vin);
}
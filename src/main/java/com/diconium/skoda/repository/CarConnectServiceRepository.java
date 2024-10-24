package com.diconium.skoda.repository;

import com.diconium.skoda.model.entity.CarConnectService;
import com.diconium.skoda.model.entity.CarConnectServiceId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarConnectServiceRepository extends JpaRepository<CarConnectService, CarConnectServiceId> {
    List<CarConnectService> findByCarVin(String vin);
}

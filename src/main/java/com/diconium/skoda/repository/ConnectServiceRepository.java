package com.diconium.skoda.repository;

import com.diconium.skoda.model.entity.ConnectService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectServiceRepository extends JpaRepository<ConnectService, Long> {
}
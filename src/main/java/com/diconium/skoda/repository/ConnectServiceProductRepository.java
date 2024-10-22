package com.diconium.skoda.repository;

import com.diconium.skoda.model.entity.ConnectServiceProduct;
import com.diconium.skoda.model.entity.ConnectServiceProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectServiceProductRepository extends JpaRepository<ConnectServiceProduct, ConnectServiceProductId> {
}
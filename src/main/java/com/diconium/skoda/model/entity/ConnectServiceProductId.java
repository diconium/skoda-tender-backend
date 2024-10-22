package com.diconium.skoda.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ConnectServiceProductId implements Serializable {

    @Column(name = "connect_service_id")
    private Integer connectServiceId;

    @Column(name = "product_id")
    private Integer productId;

    public Integer getConnectServiceId() {
        return connectServiceId;
    }

    public void setConnectServiceId(Integer serviceId) {
        this.connectServiceId = serviceId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConnectServiceProductId that = (ConnectServiceProductId) o;
        return Objects.equals(getConnectServiceId(), that.getConnectServiceId()) && Objects.equals(getProductId(), that.getProductId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getConnectServiceId(), getProductId());
    }
}
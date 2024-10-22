package com.diconium.skoda.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CarConnectServiceId implements Serializable {

    @Column(name = "connect_service_id")
    private Integer connectServiceId;

    @Column(name = "car_vin", length = 17)
    private String carVin;

    public Integer getConnectServiceId() {
        return connectServiceId;
    }

    public void setConnectServiceId(Integer serviceId) {
        this.connectServiceId = serviceId;
    }

    public String getCarVin() {
        return carVin;
    }

    public void setCarVin(String carVin) {
        this.carVin = carVin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarConnectServiceId that = (CarConnectServiceId) o;
        return Objects.equals(getConnectServiceId(), that.getConnectServiceId()) && Objects.equals(getCarVin(), that.getCarVin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getConnectServiceId(), getCarVin());
    }
}
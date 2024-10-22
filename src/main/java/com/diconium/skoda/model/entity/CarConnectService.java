package com.diconium.skoda.model.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "Cars_ConnectServices")
public class CarConnectService {

    @EmbeddedId
    private CarConnectServiceId id = new CarConnectServiceId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("carVin")
    @JoinColumn(name = "car_vin")
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("connectServiceId")
    @JoinColumn(name = "connect_service_id")
    private ConnectService connectService;

    @Column(length = 50)
    private String status;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    public CarConnectServiceId getId() {
        return id;
    }

    public void setId(CarConnectServiceId id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public ConnectService getService() {
        return connectService;
    }

    public void setService(ConnectService connectService) {
        this.connectService = connectService;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}



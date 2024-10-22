package com.diconium.skoda.model.entity;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Cars")
public class Car {

    @Id
    @Column(length = 17)
    private String vin;

    @Column(nullable = false, length = 50)
    private String brand;

    @Column(nullable = false, length = 50)
    private String model;

    @Column(nullable = false)
    private int year;

    // Many-to-One relationship: A car belongs to one user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Many-to-Many: A car can have many services, and a service can apply to many cars
    @ManyToMany
    @JoinTable(
            name = "Cars_ConnectServices",
            joinColumns = @JoinColumn(name = "car_vin"),
            inverseJoinColumns = @JoinColumn(name = "connect_service_id")
    )
    private Set<ConnectService> connectServices = new HashSet<>();

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String make) {
        this.brand = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<ConnectService> getServices() {
        return connectServices;
    }

    public void setServices(Set<ConnectService> connectServices) {
        this.connectServices = connectServices;
    }
}
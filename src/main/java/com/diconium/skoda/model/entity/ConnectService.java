package com.diconium.skoda.model.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "ConnectServices")
public class ConnectService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 255)
    private String image;

    @Column(name = "contract_length")
    private Integer contractLength;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @ManyToMany(mappedBy = "connectServices")
    private Set<Car> cars = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "ConnectServices_Products",
            joinColumns = @JoinColumn(name = "connect_service_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(final String image) {
        this.image = image;
    }

    public Integer getContractLength() {
        return contractLength;
    }

    public void setContractLength(final Integer contractLength) {
        this.contractLength = contractLength;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(final Set<Car> cars) {
        this.cars = cars;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(final Set<Product> products) {
        this.products = products;
    }
}
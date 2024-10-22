package com.diconium.skoda.model.entity;


import jakarta.persistence.*;


@Entity
@Table(name = "ConnectServices_Products")
public class ConnectServiceProduct {

    @EmbeddedId
    private ConnectServiceProductId id = new ConnectServiceProductId();

    @ManyToOne
    @MapsId("connectServiceId")
    @JoinColumn(name = "connect_service_id")
    private ConnectService connectService;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    public ConnectServiceProductId getId() {
        return id;
    }

    public void setId(ConnectServiceProductId id) {
        this.id = id;
    }

    public ConnectService getService() {
        return connectService;
    }

    public void setService(ConnectService connectService) {
        this.connectService = connectService;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

package com.diconium.skoda.model.dto;

import java.util.List;

public record SubscriptionDto(
        int id,
        String name,
        String description,
        String imageLink,
        int length,
        float price,
        List<ProductsDto> includedServices,
        String status,
        String startDate,
        String endDate) {}

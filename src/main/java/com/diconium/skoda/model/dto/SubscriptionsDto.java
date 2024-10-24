package com.diconium.skoda.model.dto;

import java.util.List;

public record SubscriptionsDto(UserDto user, CarDto car, List<SubscriptionDto> subscriptions) {}

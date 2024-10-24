package com.diconium.skoda.model.dto;

import jakarta.validation.constraints.NotBlank;

public record NotificationDto(int id, @NotBlank String vin, @NotBlank String title, @NotBlank String message) {}

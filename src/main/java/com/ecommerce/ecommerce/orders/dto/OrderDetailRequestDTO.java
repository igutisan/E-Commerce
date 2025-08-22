package com.ecommerce.ecommerce.orders.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


public record OrderDetailRequestDTO(
        @NotNull
        @Min(0)
        int quantity,
        @NotNull
        String orderId,
        @NotNull
        String productId)
{}

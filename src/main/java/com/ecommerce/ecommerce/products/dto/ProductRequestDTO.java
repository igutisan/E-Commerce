package com.ecommerce.ecommerce.products.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


import java.math.BigDecimal;
import java.util.List;

public record ProductRequestDTO(
    @NotEmpty(message = "El nombre del producto no puede estar vacío.")
    String name,


    String description,

    @NotNull(message = "El precio no puede ser nulo.")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que cero.")
    BigDecimal price,

    @Min(value = 0, message = "El stock no puede ser negativo.")
    int stock,

    @Nullable
    List<String> imageUrls
) {}

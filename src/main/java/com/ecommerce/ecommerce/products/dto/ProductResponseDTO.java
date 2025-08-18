package com.ecommerce.ecommerce.products.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ProductResponseDTO(
    String id,
    String name,
    String description,
    BigDecimal price,
    int stock,
    LocalDateTime createAt,
    LocalDateTime updatedAt,
    String userId,
    List<String> imageUrls
) {}

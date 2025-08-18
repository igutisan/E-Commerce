package com.ecommerce.ecommerce.users.dto;

public record UserResponseDTO(
        String id,
        String name,
        String email,
        String role
) {
}

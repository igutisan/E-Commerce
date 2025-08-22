package com.ecommerce.ecommerce.auth.dto;

import jakarta.validation.constraints.*;


public record UserLoginRequestDTO(
        @Email
        @NotEmpty
        String email,

        @NotEmpty
        String password) {
}

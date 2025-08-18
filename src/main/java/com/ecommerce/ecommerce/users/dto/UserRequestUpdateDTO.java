package com.ecommerce.ecommerce.users.dto;

import com.ecommerce.ecommerce.users.model.Role;

public record UserRequestUpdateDTO(
        String name,
        String email,
        String password,
        String dni,
        Role role
) {
}

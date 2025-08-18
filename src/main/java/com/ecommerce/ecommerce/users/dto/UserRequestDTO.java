package com.ecommerce.ecommerce.users.dto;

import com.ecommerce.ecommerce.users.model.Role;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UserRequestDTO(
        @NotEmpty()
         String name,

        @NotEmpty()
        @Email()
         String email,

         @NotEmpty()
         String password,

         @NotEmpty
         String dni,

         @Nullable
         Role role
) {
}

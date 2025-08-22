package com.ecommerce.ecommerce.auth.dto;

import com.ecommerce.ecommerce.users.model.Role;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;

public record UserRequestDTO(
        @NotEmpty()
         String name,

        @NotEmpty()
        @Email()
         String email,

         @NotEmpty()
        @NotBlank(message = "La contraseña no puede estar vacía")
        @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
        @Pattern(
                regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&]).+$",
                message = "La contraseña debe contener mayúscula, minúscula, número y carácter especial"
        )
         String password,

         @NotEmpty
         String dni,

         @Nullable
         Role role
) {
}

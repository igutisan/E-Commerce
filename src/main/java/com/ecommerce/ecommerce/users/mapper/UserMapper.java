package com.ecommerce.ecommerce.users.mapper;

import com.ecommerce.ecommerce.users.dto.UserRequestDTO;
import com.ecommerce.ecommerce.users.dto.UserResponseDTO;
import com.ecommerce.ecommerce.users.model.User;

public class UserMapper {

    public static User toEntity (UserRequestDTO userRequestDTO) {
        if(userRequestDTO == null){
            return  null;
        }
        return User.builder()
                .dni(userRequestDTO.dni())
                .email(userRequestDTO.email())
                .name(userRequestDTO.name())
                .password(userRequestDTO.password())
                .role(userRequestDTO.role())
                .build();
    }

    public static UserResponseDTO toDto(User user){
        if(user == null) return null;

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole().toString()
        );
    }
}

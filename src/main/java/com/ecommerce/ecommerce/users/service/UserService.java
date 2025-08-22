package com.ecommerce.ecommerce.users.service;

import com.ecommerce.ecommerce.auth.dto.UserRequestDTO;
import com.ecommerce.ecommerce.users.dto.UserRequestUpdateDTO;
import com.ecommerce.ecommerce.users.dto.UserResponseDTO;
import com.ecommerce.ecommerce.users.model.User;

public interface UserService {
    UserResponseDTO save(UserRequestDTO userRequestDTO);
    UserResponseDTO findById(String id);
    User findByIdLocal(String id);
    UserResponseDTO update(UserRequestUpdateDTO userRequestDTO, String id);
    void deleteById(String id);

}

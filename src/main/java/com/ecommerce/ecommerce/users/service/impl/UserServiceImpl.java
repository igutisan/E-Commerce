package com.ecommerce.ecommerce.users.service.impl;

import com.ecommerce.ecommerce.auth.dto.UserRequestDTO;
import com.ecommerce.ecommerce.users.dto.UserRequestUpdateDTO;
import com.ecommerce.ecommerce.users.dto.UserResponseDTO;
import com.ecommerce.ecommerce.users.mapper.UserMapper;
import com.ecommerce.ecommerce.users.model.User;
import com.ecommerce.ecommerce.users.repository.UserRepository;
import com.ecommerce.ecommerce.users.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponseDTO save(UserRequestDTO userRequestDTO) {
        if (userRequestDTO == null) {
            return null;
        }
        User user = UserMapper.toEntity(userRequestDTO);

        if(user.getPassword() != null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        User createdUser = userRepository.save(user);
        return UserMapper.toDto(createdUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO findById(String id) {
        return userRepository.findById(id).map(UserMapper::toDto)
                .orElseThrow(() ->  new RuntimeException("User not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public User findByIdLocal(String id) {
        return userRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("User not found with id: " + id));
    }

    @Override
    @Transactional()
    public UserResponseDTO update(UserRequestUpdateDTO userRequestUpdateDTO, String userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->  new RuntimeException("User not found with id: " + userId));

        user.setName(userRequestUpdateDTO.name());
        user.setEmail(userRequestUpdateDTO.email());
        if(userRequestUpdateDTO.password() != null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        user.setDni(userRequestUpdateDTO.dni());
        user.setRole(userRequestUpdateDTO.role());

        User updatedUser = userRepository.save(user);
        return UserMapper.toDto(updatedUser);

    }


    @Override
    @Transactional(readOnly = true)
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    private boolean passowordMatch(String password, String encodedPassword){
        return  passwordEncoder.matches(password, encodedPassword);
    }


}

package com.ecommerce.ecommerce.users.controller;

import com.ecommerce.ecommerce.auth.dto.TokenResponse;
import com.ecommerce.ecommerce.auth.dto.UserLoginRequestDTO;
import com.ecommerce.ecommerce.auth.service.AuthService;
import com.ecommerce.ecommerce.users.dto.*;
import com.ecommerce.ecommerce.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@Valid @RequestBody UserRequestUpdateDTO userRequestDTO, @PathVariable String id) {
        return ResponseEntity.ok(userService.update(userRequestDTO, id));
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

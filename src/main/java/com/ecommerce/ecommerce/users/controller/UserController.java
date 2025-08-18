package com.ecommerce.ecommerce.users.controller;

import com.ecommerce.ecommerce.users.dto.UserRequestDTO;
import com.ecommerce.ecommerce.users.dto.UserRequestUpdateDTO;
import com.ecommerce.ecommerce.users.dto.UserResponseDTO;
import com.ecommerce.ecommerce.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO user = userService.save(userRequestDTO);
       return ResponseEntity.created(URI.create("/api/v1/user/"+user.id())).body(user);
    }

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

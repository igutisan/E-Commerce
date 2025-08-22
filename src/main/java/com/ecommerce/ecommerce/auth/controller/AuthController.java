package com.ecommerce.ecommerce.auth.controller;

import com.ecommerce.ecommerce.auth.dto.TokenResponse;
import com.ecommerce.ecommerce.auth.dto.UserLoginRequestDTO;
import com.ecommerce.ecommerce.auth.dto.UserRequestDTO;
import com.ecommerce.ecommerce.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@Valid @RequestBody UserRequestDTO userRequestDTO){

        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(userRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody UserLoginRequestDTO loginRequestDTO)  {
       return ResponseEntity.ok(authService.login(loginRequestDTO));
    }

//    @PostMapping("/refresh")
//    public ResponseEntity<UserResponseDTO> refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) throws URISyntaxException {
//        // return refresh token
//        return ResponseEntity.status(HttpStatus.CREATED).body(user);
//    }
//
//    @PostMapping("/logout")
//    public ResponseEntity<Void> logout(HttpServletRequest request) {
//        try {
//            String authHeader = request.getHeader("Authorization");
//            authService.logout(authHeader);
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }
}
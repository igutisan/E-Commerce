package com.ecommerce.ecommerce.auth.service;

import com.ecommerce.ecommerce.auth.dto.TokenResponse;
import com.ecommerce.ecommerce.auth.dto.UserLoginRequestDTO;
import com.ecommerce.ecommerce.auth.dto.UserRequestDTO;

import com.ecommerce.ecommerce.users.model.Role;
import com.ecommerce.ecommerce.users.model.User;
import com.ecommerce.ecommerce.users.repository.TokenRepository;
import com.ecommerce.ecommerce.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private  final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public TokenResponse register(UserRequestDTO userRequestDTO) {
        var user = User.builder()
                .name(userRequestDTO.name())
                .dni(userRequestDTO.dni())
                .email(userRequestDTO.email())
                .password(passwordEncoder.encode(userRequestDTO.password()))
                .role(Role.CLIENT)
                .build();
        var savedUser = userRepository.save(user);
        return new TokenResponse(jwtService.generateToken(savedUser));

    }

    public TokenResponse login(UserLoginRequestDTO userLoginRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginRequestDTO.email(),
                        userLoginRequestDTO.password()
                )
        );
        var user = userRepository.findByEmail(userLoginRequestDTO.email())
                .orElseThrow(() -> new RuntimeException("User not found"));
        var token = jwtService.generateToken(user);
        return new TokenResponse(token);
    }


    public void logout(String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return;
        }
        String jwt = token.substring(7);
        var storedToken = tokenRepository.findByToken(jwt).orElse(null);
        if (storedToken != null) {
            storedToken.setExpired(true);
            storedToken.setRevoked(true);
            tokenRepository.save(storedToken);
        }
    }


}

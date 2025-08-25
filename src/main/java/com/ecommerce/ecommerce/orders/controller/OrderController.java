package com.ecommerce.ecommerce.orders.controller;

import com.ecommerce.ecommerce.auth.service.JwtService;
import com.ecommerce.ecommerce.orders.dto.OrderDetailRequestDTO;
import com.ecommerce.ecommerce.orders.dto.OrderResponseDTO;
import com.ecommerce.ecommerce.orders.service.CreateOrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrderService createOrderService;
    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody List<OrderDetailRequestDTO> orderDetailRequestDTOS, HttpServletRequest request) {
        String token = getTokenFromRequest(request);
        String userId = jwtService.getUserIdFromToken(token);
        OrderResponseDTO orderResponseDTO = createOrderService.createOrder(userId, orderDetailRequestDTOS);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderResponseDTO);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        var authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Token not found or invalid");
        }
        return authHeader.substring(7);
    }
}

package com.ecommerce.ecommerce.orders.controller;

import com.ecommerce.ecommerce.orders.dto.OrderDetailRequestDTO;
import com.ecommerce.ecommerce.orders.dto.OrderResponseDTO;
import com.ecommerce.ecommerce.orders.service.CreateOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrderService createOrderService;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(String userId, List<OrderDetailRequestDTO> orderDetailRequestDTOS) {
        OrderResponseDTO orderResponseDTO = createOrderService.createOrder(userId, orderDetailRequestDTOS);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderResponseDTO);
    }
}

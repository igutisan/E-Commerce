package com.ecommerce.ecommerce.orders.dto;

import com.ecommerce.ecommerce.orders.model.Order;
import com.ecommerce.ecommerce.users.dto.UserResponseDTO;

import java.util.Date;
import java.util.List;

public record OrderResponseDTO(String id, Date orderDate, UserResponseDTO user, List<Order>orders) {
}

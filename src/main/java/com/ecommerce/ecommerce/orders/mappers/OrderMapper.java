package com.ecommerce.ecommerce.orders.mappers;

import com.ecommerce.ecommerce.orders.dto.OrderResponseDTO;
import com.ecommerce.ecommerce.orders.model.Order;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderResponseDTO toDto(Order order);

    List<OrderResponseDTO> toDtoList(List<Order> orders);
}

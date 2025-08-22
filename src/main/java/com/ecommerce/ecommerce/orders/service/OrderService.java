package com.ecommerce.ecommerce.orders.service;


import com.ecommerce.ecommerce.orders.dto.OrderResponseDTO;
import com.ecommerce.ecommerce.orders.mappers.OrderMapper;
import com.ecommerce.ecommerce.orders.model.Order;
import com.ecommerce.ecommerce.orders.repository.OrderRepository;
import com.ecommerce.ecommerce.users.model.User;
import com.ecommerce.ecommerce.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final OrderMapper orderMapper;

    public Order create(String userId){
        User user = userService.findByIdLocal(userId);

        Order order = new Order();
        order.setUser(user);
        order= orderRepository.save(order);

        return order;
    }

    public List<OrderResponseDTO> getAllByUserID (String userId){
        List<Order> orders = orderRepository.findAllByUserId(userId);
        return orderMapper.toDtoList(orders);
    }
}

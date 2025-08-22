package com.ecommerce.ecommerce.orders.service;


import com.ecommerce.ecommerce.orders.dto.OrderDetailRequestDTO;
import com.ecommerce.ecommerce.orders.mappers.OrderDetailMapper;
import com.ecommerce.ecommerce.orders.model.OrderDetail;
import com.ecommerce.ecommerce.orders.repository.OrderDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDetailsService {
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderDetailMapper orderDetailMapper;

    public void create(OrderDetailRequestDTO orderDetailRequestDTO){
        OrderDetail orderDetail= orderDetailMapper.toEntity(orderDetailRequestDTO);

        orderDetail = orderDetailsRepository.save(orderDetail);

    }

    public OrderDetail getOrderDetail(String orderId){
        return orderDetailsRepository.findById(orderId).orElseThrow();
    }

}

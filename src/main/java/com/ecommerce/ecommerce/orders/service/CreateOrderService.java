package com.ecommerce.ecommerce.orders.service;

import com.ecommerce.ecommerce.orders.dto.OrderDetailRequestDTO;
import com.ecommerce.ecommerce.orders.dto.OrderResponseDTO;
import com.ecommerce.ecommerce.orders.mappers.OrderMapper;
import com.ecommerce.ecommerce.orders.model.Order;
import com.ecommerce.ecommerce.orders.model.OrderDetail;
import com.ecommerce.ecommerce.orders.repository.OrderDetailsRepository;
import com.ecommerce.ecommerce.products.dto.ProductResponseDTO;
import com.ecommerce.ecommerce.products.model.Product;
import com.ecommerce.ecommerce.products.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreateOrderService {
    private final OrderService orderService;
   private final OrderDetailsRepository orderDetailsRepository;
    private final ProductServiceImpl productService;
    private final OrderMapper orderMapper;



    @Transactional
    public OrderResponseDTO createOrder(String userId, List<OrderDetailRequestDTO> orderDetailRequestDTOS) {
        Order order = orderService.create(userId);



        Set<String> productIds = orderDetailRequestDTOS.stream()
                .map(OrderDetailRequestDTO::productId)
                .collect(Collectors.toSet());

        List<Product> products = productService.findAllById(productIds);


        Map<String, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, p -> p));

        List<OrderDetail> orderDetails = new ArrayList<>();

        for (OrderDetailRequestDTO detailDTO : orderDetailRequestDTOS) {
            Product product = productMap.get(detailDTO.productId());
            if (product == null) {
                throw new RuntimeException("Product not found: " + detailDTO.productId());
            }

            OrderDetail detail = new OrderDetail();
            detail.setQuantity(detailDTO.quantity());
            detail.setUnitPrice(product.getPrice());
            detail.setOrder(order);
            detail.setProduct(product);

            orderDetails.add(detail);
        }

        orderDetailsRepository.saveAll(orderDetails);

        return orderMapper.toDto(order);
    }

}

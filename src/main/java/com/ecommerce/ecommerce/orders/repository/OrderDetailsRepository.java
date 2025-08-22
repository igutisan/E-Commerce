package com.ecommerce.ecommerce.orders.repository;

import com.ecommerce.ecommerce.orders.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetail, String> {
}

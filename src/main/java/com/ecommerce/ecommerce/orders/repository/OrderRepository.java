package com.ecommerce.ecommerce.orders.repository;

import com.ecommerce.ecommerce.orders.model.Order;
import com.ecommerce.ecommerce.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findAllByUserId(String userId);

    String user(User user);
}

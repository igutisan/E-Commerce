package com.ecommerce.ecommerce.products.repository;

import com.ecommerce.ecommerce.products.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {


    List<Product> findByNameContainingIgnoreCase(String name);

    Optional<Product> findByIdAndUserId(String id, String userId);

    List<Product> findAllByUserId(String userId);

    void deleteByIdAndUserId(String id, String userId);
}

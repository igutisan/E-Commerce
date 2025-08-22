package com.ecommerce.ecommerce.products.service;

import com.ecommerce.ecommerce.products.dto.ProductRequestDTO;
import com.ecommerce.ecommerce.products.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductService {


    Product create(ProductRequestDTO productRequestDTO);

    List<Product> findAll();

    List<Product> findAllById(Set<String> productIds);

    Product findById(String id);


    Product update(String id, ProductRequestDTO productRequestDTO);


    void deleteById(String id);


    List<Product> findByName(String name);
}

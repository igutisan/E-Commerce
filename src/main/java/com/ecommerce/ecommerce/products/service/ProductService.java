package com.ecommerce.ecommerce.products.service;

import com.ecommerce.ecommerce.products.dto.ProductRequestDTO;
import com.ecommerce.ecommerce.products.dto.ProductUpdateDTO;
import com.ecommerce.ecommerce.products.model.Product;
import com.ecommerce.ecommerce.users.model.User;

import java.util.List;
import java.util.Set;

public interface ProductService {


    Product create(User user, ProductRequestDTO productRequestDTO);

    List<Product> findAll(User user);

    List<Product> findAllById(Set<String> productIds);

    Product findById(String id, User user);


    Product update(String id, ProductUpdateDTO productUpdateDto, User user);


    void deleteById(String id, User user);


    List<Product> findByName(String name);
}

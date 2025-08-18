package com.ecommerce.ecommerce.products.service;

import com.ecommerce.ecommerce.products.dto.ProductRequestDTO;
import com.ecommerce.ecommerce.products.dto.ProductResponseDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {


    ProductResponseDTO create(ProductRequestDTO productRequestDTO);

    List<ProductResponseDTO> findAll();


    ProductResponseDTO findById(String id);


    ProductResponseDTO update(String id, ProductRequestDTO productRequestDTO);


    void deleteById(String id);

    List<ProductResponseDTO> findByName(String name);
}

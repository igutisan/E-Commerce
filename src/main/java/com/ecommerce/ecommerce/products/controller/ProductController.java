package com.ecommerce.ecommerce.products.controller;

import com.ecommerce.ecommerce.products.dto.ProductRequestDTO;
import com.ecommerce.ecommerce.products.dto.ProductResponseDTO;
import com.ecommerce.ecommerce.products.mapper.ProductMapper;
import com.ecommerce.ecommerce.products.model.Product;
import com.ecommerce.ecommerce.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        Product product = productService.create(productRequestDTO);
        ProductResponseDTO productResponseDTO = ProductMapper.toProductResponseDTO(product);
        return  ResponseEntity.created(URI.create("api/v1/products/"+productResponseDTO.id()))
                .body(productResponseDTO);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable String id, @RequestBody ProductRequestDTO productRequestDTO) {
        Product product = productService.update(id, productRequestDTO);
        return ResponseEntity.ok(ProductMapper.toProductResponseDTO(product));
    }
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<Product> products = productService.findAll();
        List<ProductResponseDTO> productResponseDTOs = products.stream()
                .map(ProductMapper::toProductResponseDTO)
                .toList();
        return ResponseEntity.ok(productResponseDTOs);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable String id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok(ProductMapper.toProductResponseDTO(product));
    }

}

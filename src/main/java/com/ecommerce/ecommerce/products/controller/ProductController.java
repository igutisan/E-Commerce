package com.ecommerce.ecommerce.products.controller;

import com.ecommerce.ecommerce.products.dto.ProductRequestDTO;
import com.ecommerce.ecommerce.products.dto.ProductResponseDTO;
import com.ecommerce.ecommerce.products.dto.ProductUpdateDTO;
import com.ecommerce.ecommerce.products.mapper.ProductMapper;
import com.ecommerce.ecommerce.products.model.Product;
import com.ecommerce.ecommerce.products.service.ProductService;
import com.ecommerce.ecommerce.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO, Authentication authentication) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = (User) userDetails;
            Product product = productService.create(user,productRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(ProductMapper.toProductResponseDTO(product));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable String id, @RequestBody ProductUpdateDTO productUpdateDto, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = (User) userDetails;
        Product product = productService.update(id, productUpdateDto, user);
        return ResponseEntity.ok(ProductMapper.toProductResponseDTO(product));
    }
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = (User) userDetails;
        List<Product> products = productService.findAll(user);
        List<ProductResponseDTO> productResponseDTOs = products.stream()
                .map(ProductMapper::toProductResponseDTO)
                .toList();
        return ResponseEntity.ok(productResponseDTOs);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = (User) userDetails;
        productService.deleteById(id, user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable String id, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = (User) userDetails;
        Product product = productService.findById(id, user);
        return ResponseEntity.ok(ProductMapper.toProductResponseDTO(product));
    }

}

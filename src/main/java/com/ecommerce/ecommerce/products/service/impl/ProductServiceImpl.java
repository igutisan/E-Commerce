package com.ecommerce.ecommerce.products.service.impl;

import com.ecommerce.ecommerce.products.dto.ProductRequestDTO;
import com.ecommerce.ecommerce.products.dto.ProductResponseDTO;
import com.ecommerce.ecommerce.products.mapper.ProductMapper;
import com.ecommerce.ecommerce.products.model.Image;
import com.ecommerce.ecommerce.products.model.Product;
import com.ecommerce.ecommerce.products.repository.ImageRepository;
import com.ecommerce.ecommerce.products.repository.ProductRepository;
import com.ecommerce.ecommerce.products.service.ProductService;
import com.ecommerce.ecommerce.users.model.User;
import com.ecommerce.ecommerce.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserService userService;
    private final ImageRepository imageRepository;



    @Override
    @Transactional
    public ProductResponseDTO create(ProductRequestDTO productRequestDTO) {
        User user = userService.findByIdLocal(productRequestDTO.userId());

        Product product = ProductMapper.toProduct(productRequestDTO, user);
        Product savedProduct = productRepository.save(product);
        if (productRequestDTO.imageUrls() != null && !productRequestDTO.imageUrls().isEmpty()){
            List<Image> images = productRequestDTO.imageUrls().stream().map(i -> {
                Image image = new Image();
                image.setUrl(i);
                image.setProduct(savedProduct);
                return image;
            }).toList();

            imageRepository.saveAll(images);
        }
        return ProductMapper.toProductResponseDTO(savedProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDTO> findAll() {
        return productRepository.findAll().stream()
                .map(ProductMapper::toProductResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponseDTO findById(String id) {
        return productRepository.findById(id)
                .map(ProductMapper::toProductResponseDTO).orElseThrow(() -> new RuntimeException("product not found"));
    }

    @Override
    @Transactional
    public ProductResponseDTO update(String id, ProductRequestDTO productRequestDTO) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    User user = userService.findByIdLocal(productRequestDTO.userId());

                    existingProduct.setName(productRequestDTO.name());
                    existingProduct.setDescription(productRequestDTO.description());
                    existingProduct.setPrice(productRequestDTO.price());
                    existingProduct.setStock(productRequestDTO.stock());
                    existingProduct.setUser(user);
                    // TODO: Implement image update logic. 
                    // This might involve clearing the existing list and adding new images,
                    // or more complex logic to compare and update individual images.
                    
                    Product updatedProduct = productRepository.save(existingProduct);
                    return ProductMapper.toProductResponseDTO(updatedProduct);
                }).orElseThrow(() -> new RuntimeException("product not found"));
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDTO> findByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name).stream()
                .map(ProductMapper::toProductResponseDTO)
                .collect(Collectors.toList());
    }
}

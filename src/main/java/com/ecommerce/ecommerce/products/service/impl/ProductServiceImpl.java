package com.ecommerce.ecommerce.products.service.impl;

import com.ecommerce.ecommerce.products.dto.ProductRequestDTO;
import com.ecommerce.ecommerce.products.dto.ProductUpdateDTO;
import com.ecommerce.ecommerce.products.mapper.ProductMapper;
import com.ecommerce.ecommerce.products.model.Image;
import com.ecommerce.ecommerce.products.model.Product;
import com.ecommerce.ecommerce.products.repository.ImageRepository;
import com.ecommerce.ecommerce.products.repository.ProductRepository;
import com.ecommerce.ecommerce.products.service.ProductService;
import com.ecommerce.ecommerce.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;



    @Override
    @Transactional
    public Product create(User user, ProductRequestDTO productRequestDTO) {

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
        return savedProduct;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll(User user) {
        return productRepository.findAllByUserId((user.getId()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAllById(Set<String> productIds) {

        return productRepository.findAll();
        }

    @Override
    @Transactional(readOnly = true)
    public Product findById(String id, User user) {
        return productRepository.findByIdAndUserId((id), user.getId())
                .orElseThrow(() -> new RuntimeException("product not found"));
    }

    @Override
    @Transactional
    public Product update(String id, ProductUpdateDTO productUpdateDTO, User user) {
        return productRepository.findByIdAndUserId(id, user.getId())
                .map(existingProduct -> {
                    existingProduct.setName(productUpdateDTO.name()!= null ? productUpdateDTO.name() : existingProduct.getName());
                    existingProduct.setDescription(productUpdateDTO.description() != null ? productUpdateDTO.description() : existingProduct.getDescription());
                    existingProduct.setPrice(productUpdateDTO.price() != null ? productUpdateDTO.price() : existingProduct.getPrice());
                    //existingProduct.setStock(productUpdateDTO.stock() == 0 ? productUpdateDTO.stock(): existingProduct.getStock());
                    existingProduct.setUser(user);
                    // TODO: Implement image update logic. 
                    // This might involve clearing the existing list and adding new images,
                    // or more complex logic to compare and update individual images.
                    
                    return productRepository.save(existingProduct);
                }).orElseThrow(() -> new RuntimeException("product not found"));
    }

    @Override
    @Transactional
    public void deleteById(String id, User user) {
        productRepository.deleteByIdAndUserId(id, user.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }
}

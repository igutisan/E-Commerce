package com.ecommerce.ecommerce.products.mapper;

import com.ecommerce.ecommerce.products.dto.ProductRequestDTO;
import com.ecommerce.ecommerce.products.dto.ProductResponseDTO;
import com.ecommerce.ecommerce.products.model.Image;
import com.ecommerce.ecommerce.products.model.Product;
import com.ecommerce.ecommerce.users.model.User;

import java.util.Collections;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductResponseDTO toProductResponseDTO(Product product) {
        if (product == null) {
            return null;
        }

        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCreateAt(),
                product.getUpdatedAt(),
                product.getUser() != null ? product.getUser().getId() : null,
                product.getImage() != null ? product.getImage().stream().map(Image::getUrl).collect(Collectors.toList()) : Collections.emptyList()
        );
    }

    public static Product toProduct(ProductRequestDTO productRequestDTO, User user) {
        if (productRequestDTO == null) {
            return null;
        }

        Product product = new Product();
        product.setName(productRequestDTO.name());
        product.setDescription(productRequestDTO.description());
        product.setPrice(productRequestDTO.price());
        product.setStock(productRequestDTO.stock());
        product.setUser(user);

        if (productRequestDTO.imageUrls() != null && !productRequestDTO.imageUrls().isEmpty()) {
            product.setImage(productRequestDTO.imageUrls().stream().map(url -> {
                Image image = new Image();
                image.setUrl(url);
                image.setProduct(product);
                return image;
            }).collect(Collectors.toList()));
        } else {
            product.setImage(Collections.emptyList());
        }


        return product;
    }
}

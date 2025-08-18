package com.ecommerce.ecommerce.products.repository;

import com.ecommerce.ecommerce.products.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, String> {
}

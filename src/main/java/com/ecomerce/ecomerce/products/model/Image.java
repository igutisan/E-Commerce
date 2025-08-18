package com.ecomerce.ecomerce.products.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "images")
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String url;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}

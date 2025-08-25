package com.ecommerce.ecommerce.products.dto;



import java.math.BigDecimal;
import java.util.List;

public record ProductUpdateDTO(

        String name,


        String description,



        BigDecimal price,


        int stock,


        List<String> imageUrls
) {
}

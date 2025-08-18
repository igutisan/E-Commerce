package com.ecomerce.ecomerce.orders.model;

import com.ecomerce.ecomerce.products.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "order_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private int quantity;

    @OneToOne(cascade = CascadeType.ALL)
    private Order order;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Product> products;

}

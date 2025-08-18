package com.ecomerce.ecomerce.users.model;


import com.ecomerce.ecomerce.products.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String email;

    private String password;

    private Role role;

    @OneToMany(mappedBy = "user",
               cascade = CascadeType.ALL,
                orphanRemoval = true)
    private List<Product> products;

}

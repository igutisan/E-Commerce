package com.ecommerce.ecommerce.seed;

import com.ecommerce.ecommerce.products.model.Image;
import com.ecommerce.ecommerce.products.model.Product;
import com.ecommerce.ecommerce.products.repository.ImageRepository;
import com.ecommerce.ecommerce.products.repository.ProductRepository;
import com.ecommerce.ecommerce.users.model.Role;
import com.ecommerce.ecommerce.users.model.User;
import com.ecommerce.ecommerce.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SeedService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;

    @Transactional
    public boolean seed() {
        if (userRepository.count() > 0) {
            return false;
        }

        // Create Users
        User adminUser = User.builder()
                .name("Admin User")
                .email("admin@example.com")
                .password("adminpass")
                .dni("12345678A")
                .role(Role.ADMIN)
                .createAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        User clientUser1 = User.builder()
                .name("Client User 1")
                .email("client1@example.com")
                .password("clientpass1")
                .dni("87654321B")
                .role(Role.CLIENT)
                .createAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        User clientUser2 = User.builder()
                .name("Client User 2")
                .email("client2@example.com")
                .password("clientpass2")
                .dni("11223344C")
                .role(Role.CLIENT)
                .createAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        userRepository.saveAll(List.of(adminUser, clientUser1, clientUser2));

        // Create Products
        Product product1 = Product.builder()
                .name("Laptop Pro")
                .description("High-performance laptop for professionals.")
                .price(new BigDecimal("1200.00"))
                .stock(50)
                .user(adminUser) // Admin owns this product
                .createAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Product product2 = Product.builder()
                .name("Wireless Mouse")
                .description("Ergonomic wireless mouse with long battery life.")
                .price(new BigDecimal("25.99"))
                .stock(200)
                .user(adminUser) // Admin owns this product
                .createAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Product product3 = Product.builder()
                .name("Mechanical Keyboard")
                .description("Gaming mechanical keyboard with RGB lighting.")
                .price(new BigDecimal("80.50"))
                .stock(100)
                .user(clientUser1)
                .createAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Product product4 = Product.builder()
                .name("USB-C Hub")
                .description("Multi-port USB-C hub for modern laptops.")
                .price(new BigDecimal("35.00"))
                .stock(150)
                .user(clientUser2)
                .createAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        productRepository.saveAll(List.of(product1, product2, product3, product4));

        // Create Images
        Image image1_p1 = new Image();
        image1_p1.setUrl("https://example.com/images/laptop_pro_1.jpg");
        image1_p1.setProduct(product1);

        Image image2_p1 = new Image();
        image2_p1.setUrl("https://example.com/images/laptop_pro_2.jpg");
        image2_p1.setProduct(product1);

        Image image1_p2 = new Image();
        image1_p2.setUrl("https://example.com/images/mouse_1.jpg");
        image1_p2.setProduct(product2);

        Image image1_p3 = new Image();
        image1_p3.setUrl("https://example.com/images/keyboard_1.jpg");
        image1_p3.setProduct(product3);

        imageRepository.saveAll(List.of(image1_p1, image2_p1, image1_p2, image1_p3));

        return true;
    }
}

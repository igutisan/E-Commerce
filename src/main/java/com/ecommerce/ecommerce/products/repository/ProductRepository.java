package com.ecommerce.ecommerce.products.repository;

import com.ecommerce.ecommerce.products.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    /**
     * Busca productos cuyo nombre contenga el término de búsqueda, ignorando mayúsculas y minúsculas.
     *
     * @param name El término de búsqueda para el nombre del producto.
     * @return Una lista de productos que coinciden con el criterio de búsqueda.
     */
    List<Product> findByNameContainingIgnoreCase(String name);
}

package ru.yakimov.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.yakimov.persistence.entities.Product;
import ru.yakimov.persistence.entities.enums.ProductCategory;
import ru.yakimov.persistence.entities.enums.ProductStatus;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findAll();
    List<Product> findAllByCategoryAndAvailable(ProductCategory category, Boolean available);
    List<Product> findAllByAvailable(Boolean available);
    List<Product> findAllByCategory(ProductCategory category);

    Product findByTitle(String title);
}
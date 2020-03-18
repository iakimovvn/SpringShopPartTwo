package ru.yakimov.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.yakimov.entities.Product;
import ru.yakimov.entities.enums.ProductCategory;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findAll();
    List<Product> findAllByCategory(ProductCategory category);
}
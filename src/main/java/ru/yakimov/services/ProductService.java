package ru.yakimov.services;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import ru.yakimov.exceptions.ProductNotFoundException;
import ru.yakimov.persistence.entities.Product;
import ru.yakimov.persistence.entities.enums.ProductCategory;
import ru.yakimov.persistence.repositories.ProductRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product findOneById(UUID uuid) throws ProductNotFoundException {
        return productRepository.findById(uuid).orElseThrow(
            () -> new ProductNotFoundException("Oops! Product " + uuid + " wasn't found!")
        );
    }

    public List<Product> findAll(Integer category) {
        return category == null ? productRepository.findAll() : productRepository.findAllByCategory(ProductCategory.values()[category]);
    }

}
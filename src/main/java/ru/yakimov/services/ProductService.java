package ru.yakimov.services;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.yakimov.exceptions.ProductNotFoundException;
import ru.yakimov.persistence.entities.Image;
import ru.yakimov.persistence.entities.Product;
import ru.yakimov.persistence.entities.enums.ProductCategory;
import ru.yakimov.persistence.pojo.ProductPojo;
import ru.yakimov.persistence.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product findOneById(UUID uuid) throws ProductNotFoundException {
        return productRepository.findById(uuid).orElseThrow(
            () -> new ProductNotFoundException("Oops! Product " + uuid + " wasn't found!")
        );
    }

    public List<Product> findAll(Integer category, Integer status) {
        if(category == null && (status == null || status != 1))
            return productRepository.findAll();

        if(category != null && status == 1)
            return productRepository.findAllByCategoryAndAvailable(ProductCategory.values()[category]
                    , true);
        if(category != null)
            return productRepository.findAllByCategory(ProductCategory.values()[category]);

         return productRepository.findAllByAvailable(true);

    }

    public Product findByTitle(String title){
        return productRepository.findByTitle(title);
    }

    @Transactional
    public String save(ProductPojo productPogo, Image image) {

        Product product = Product.builder()
                .added(new Date())
                .title(productPogo.getTitle())
                .description(productPogo.getDescription())
                .price(productPogo.getPrice())
                .available(productPogo.isAvailable())
                .category(productPogo.getCategory())
                .images(Stream.of(image).collect(Collectors.toList()))
                .build();

        productRepository.save(product);
        log.info("New Product has been succesfully added! {}", product);
        return "redirect:/";
    }

}
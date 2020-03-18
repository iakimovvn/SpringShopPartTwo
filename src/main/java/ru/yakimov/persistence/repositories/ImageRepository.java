package ru.yakimov.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ru.yakimov.entities.Image;

import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {
    @Query(value = "SELECT image.name FROM image WHERE image.id = :id", nativeQuery = true)
    String obtainImageNameByProductId(@Param("id") UUID id);
}
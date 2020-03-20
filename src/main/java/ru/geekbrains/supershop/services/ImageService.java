package ru.geekbrains.supershop.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import ru.geekbrains.supershop.persistence.repositories.ImageRepository;
import ru.geekbrains.supershop.utils.Validators;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {

    private final static String STATIC_ICONS_PATH = "/static/icons/";

    private final ImageRepository imageRepository;

    private String getImageForSpecificProduct(UUID id) {
        return imageRepository.obtainImageNameByProductId(id);
    }

    public BufferedImage loadFileAsResource(String id) throws IOException {
        try {

            Resource resource;
            String imageName;

            if (Validators.isUUID(id)) {
                imageName = getImageForSpecificProduct(UUID.fromString(id));
                resource = new ClassPathResource("/static/images/" + imageName );
            } else {
                imageName = id;
                resource = new ClassPathResource(STATIC_ICONS_PATH + imageName );
            }
            return resource.exists() ? ImageIO.read(resource.getFile()) : ImageIO.read(new ClassPathResource(STATIC_ICONS_PATH + "image_not_found.png").getFile());
        } catch (MalformedInputException ex) {
            return null;
        }
    }

}
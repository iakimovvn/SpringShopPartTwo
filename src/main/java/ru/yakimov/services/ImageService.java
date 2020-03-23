package ru.yakimov.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.yakimov.exceptions.IncorrectImageTypeException;
import ru.yakimov.persistence.entities.Image;
import ru.yakimov.persistence.repositories.ImageRepository;
import ru.yakimov.utils.Validators;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.charset.MalformedInputException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {

    private final List<String> imageTypes = Stream.of("jpg", "png", "ico").collect(Collectors.toList());

    @Value("${files.storepath.images}")
    private Path IMAGES_STORE_PATH;

    @Value("${files.storepath.icons}")
    private Path ICONS_STORE_PATH;

    private final ImageRepository imageRepository;

    private String getImageForSpecificProduct(UUID id) {
        return imageRepository.obtainImageNameByProductId(id);
    }

    public BufferedImage loadFileAsResource(String id) {
        String imageName = null;

        try {
            Path filePath;

            if (Validators.isUUID(id)) {

                imageName = getImageForSpecificProduct(UUID.fromString(id));

                if (imageName != null) {
                    filePath = IMAGES_STORE_PATH.resolve(imageName).normalize();
                } else {
                    imageName = "image_not_found.png";
                    filePath = ICONS_STORE_PATH.resolve(imageName).normalize();
                }
            } else {
                filePath = ICONS_STORE_PATH.resolve("cart.png").normalize();
            }

            if (filePath != null) {
                return ImageIO.read(new UrlResource(filePath.toUri()).getFile());
            } else {
                throw new IOException();
            }

        } catch (IOException ex) {
            log.error("Error! Image {} file wasn't found!", imageName);
            return null;
        }
    }

    @Transactional
    public Image uploadImage(MultipartFile image, String imageName) throws IOException, IncorrectImageTypeException {
        String imageType = Objects.requireNonNull(image.getOriginalFilename()).split(".")[0];
        if(!imageTypes.contains(imageType)){
            throw new IncorrectImageTypeException("This type "+ imageType);
        }
        String uploadedFileName = imageName + "." +imageType;
        Path targetLocation = IMAGES_STORE_PATH.resolve(uploadedFileName);
        Files.copy(image.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        log.info("File {} has been succesfully uploaded!", uploadedFileName);
        return imageRepository.save(new Image(uploadedFileName, null));
    }

}
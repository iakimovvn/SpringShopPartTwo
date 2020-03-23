package ru.yakimov.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import ru.yakimov.exceptions.ProductNotFoundException;
import ru.yakimov.persistence.entities.Image;
import ru.yakimov.persistence.pojo.ProductPojo;
import ru.yakimov.services.ImageService;
import ru.yakimov.services.ProductService;
import ru.yakimov.utils.Validators;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ImageService imageService;
    private final ProductService productService;

    @GetMapping("/{id}")
    public String getOneProduct(Model model, @PathVariable String id) throws ProductNotFoundException {

//        UUID uuid = UUID.fromString(id);
//        if(Validators.isUUID(id)){
//            throw new ProductNotFoundException("Id not valid");
//        }
        model.addAttribute("product", productService.findOneById(UUID.fromString(id)));
        return "product";
    }

    @GetMapping(value = "/images/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getImage(@PathVariable String id) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedImage bufferedImage = imageService.loadFileAsResource(id);
        if (bufferedImage != null) {
            ImageIO.write(bufferedImage,"png", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } else {
            return new byte[0];
        }
    }

}
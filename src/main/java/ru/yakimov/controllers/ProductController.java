package ru.yakimov.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.yakimov.exceptions.ProductNotFoundException;
import ru.yakimov.services.ImageService;
import ru.yakimov.services.ProductService;
import ru.yakimov.utils.PatternUUID;

import javax.imageio.ImageIO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ImageService imageService;
    private final ProductService productService;
    private final PatternUUID patternUUID;

    @GetMapping("/{id}")
    public String getOneProduct(Model model, @PathVariable String id) throws ProductNotFoundException {

        UUID uuid = UUID.fromString(id);
        patternUUID.checkUUID(uuid);
        model.addAttribute("product", productService.findOneById(uuid));
        return "product";
    }

    @GetMapping(value = "/images/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getImage(@PathVariable String id) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(imageService.loadFileAsResource(id), "png", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}
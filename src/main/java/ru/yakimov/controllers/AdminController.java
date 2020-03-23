package ru.yakimov.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.yakimov.exceptions.IncorrectImageTypeException;
import ru.yakimov.persistence.entities.Image;
import ru.yakimov.persistence.entities.Product;
import ru.yakimov.persistence.entities.enums.ProductCategory;
import ru.yakimov.persistence.pojo.ProductPojo;
import ru.yakimov.services.ImageService;
import ru.yakimov.services.ProductService;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;
    private final ImageService imageService;

    @GetMapping
    public String adminPage(Model model, @CookieValue(value = "data", required = false) String data, Principal principal) {

        if (principal == null) {
            return "redirect:/";
        }

        if (data != null) {
            System.out.println(data);
        }

        model.addAttribute("products", productService.findAll(null,null));

        return "admin";
    }


    @PostMapping
    public String addOne(@RequestParam("image") MultipartFile image, ProductPojo productPojo) throws IOException, IncorrectImageTypeException {
        Image img = imageService.uploadImage(image, productPojo.getTitle());
        return productService.save(productPojo, img);
    }


}

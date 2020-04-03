package ru.geekbrains.supershop.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.supershop.exceptions.EntityNotFoundException;
import ru.geekbrains.supershop.services.ReviewService;
import ru.geekbrains.supershop.services.feign.clients.ShopFeignClient;

import java.util.UUID;

@Controller
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ShopFeignClient shopFeignClient;
    private final ReviewService reviewService;

    @GetMapping("/flyer")
    public ResponseEntity<byte[]> getFlyer() {
        return shopFeignClient.getFlyer();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String moderateReview(@PathVariable UUID id, @RequestParam String option) throws EntityNotFoundException {
        return "redirect:/products/" + reviewService.moderate(id, option);
    }

}
package ru.geekbrains.supershop.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.supershop.exceptions.EntityNotFoundException;
import ru.geekbrains.supershop.services.ReviewService;

import java.util.UUID;

@Controller
@RequestMapping("/reviews")
@RequiredArgsConstructor
@Api("Контроллер модерации комемнтариев")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "Метод модерации комментариев")
    public String moderateReview(@PathVariable UUID id, @RequestParam String option) throws EntityNotFoundException {
        return "redirect:/products/" + reviewService.moderate(id, option);
    }

}
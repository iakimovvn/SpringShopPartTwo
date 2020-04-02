package ru.geekbrains.supershop.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.geekbrains.supershop.beans.Cart;
import ru.geekbrains.supershop.services.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.UUID;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
@Api("Кортроллер корзины")
public class CartController {

    private final Cart cart;
    private final ProductService productService;

    @GetMapping("/add/{id}")
    @ApiOperation(value = "Добавление продукта в корзину")
    public void addProductToCart(@PathVariable UUID id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        cart.add(productService.findOneById(id));
        response.sendRedirect(request.getHeader("referer"));
    }

    @GetMapping("/remove/{id}")
    @ApiOperation(value = "Удаление продукта в корзину")
    public String removeProductFromCart(@PathVariable UUID id) {
        cart.removeByProductId(id);
        return "redirect:/cart";
    }

    @GetMapping
    @ApiOperation(value = "Показать корзину")
    public String showCart(Model model) {
        model.addAttribute("cart", cart);
        return "cart";
    }

}
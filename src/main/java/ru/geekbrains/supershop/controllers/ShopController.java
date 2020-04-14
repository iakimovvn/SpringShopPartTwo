package ru.geekbrains.supershop.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.geekbrains.paymentservice.Payment;
import ru.geekbrains.supershop.beans.Cart;
import ru.geekbrains.supershop.persistence.entities.CartRecord;
import ru.geekbrains.supershop.persistence.entities.Purchase;
import ru.geekbrains.supershop.persistence.entities.Shopuser;
import ru.geekbrains.supershop.services.ProductService;

import ru.geekbrains.supershop.services.PurchaseService;
import ru.geekbrains.supershop.services.ReviewService;
import ru.geekbrains.supershop.services.ShopuserService;
import ru.geekbrains.supershop.utils.CaptchaGenerator;
import ru.geekbrains.supershop.utils.Validators;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ShopController {

    private final Cart cart;
    private final CaptchaGenerator captchaGenerator;
    private final ProductService productService;
    private final PurchaseService purchaseService;
    private final ReviewService reviewService;
    private final ShopuserService shopuserService;

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String index(Model model,
        @RequestParam(required = false) Integer category,
        @RequestParam(required = false) Integer minPrice,
        @RequestParam(required = false) Integer maxPrice,
        @RequestParam(required = false) Boolean notAvailable)
    {
        model.addAttribute("cart", cart.getCartRecords());
        model.addAttribute("products", productService.findAll(category, minPrice, maxPrice, notAvailable));
        return "index";
    }

    @GetMapping("/admin")
    public String adminPage(Model model, @CookieValue(value = "data", required = false) String data, Principal principal) {

        if (principal == null) {
            return "redirect:/";
        }

        if (data != null) {
            System.out.println(data);
        }

        model.addAttribute("products", productService.findAll(null));

        return "admin";
    }

    @GetMapping("/profile")
    public String profilePage(Model model, @CookieValue(value = "data", required = false) String data, Principal principal) {

        if (principal == null) {
            return "redirect:/";
        }

        Shopuser shopuser = shopuserService.findByPhone(principal.getName());

        model.addAttribute("reviews", reviewService.getReviewsByShopuser(shopuser).orElse(new ArrayList<>()));
        model.addAttribute("shopuser", shopuser);

        if (data != null) {
            System.out.println(data);
        }

        return "profile";
    }

    @GetMapping(value = "/captcha", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] captcha(HttpSession session) {
        try {
            BufferedImage img = captchaGenerator.getCaptchaImage();
            session.setAttribute("captchaCode", captchaGenerator.getCaptchaString());
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            ImageIO.write(img, "png", bao);
            return bao.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/checkout")
    public String proceedToCheckout(String paymentId, Model model) {

        Payment payment = cart.getPayments()
                .stream()
                .filter(p -> p.getId() == Integer.valueOf(paymentId))
                .collect(Validators.toSingleton());

        cart.setPayment(payment);

        model.addAttribute("cart", cart);

        return "checkout";

    }

    @PostMapping("/purchase")
    public String finishOrderAndPay(String phone, String email, Principal principal, Model model) {

        Shopuser shopuser = shopuserService.findByPhone(principal.getName());

        Purchase purchase = Purchase.builder()
            .shopuser(shopuser)
            .products(cart.getCartRecords()
                .stream()
                .map(CartRecord::getProduct)
                .collect(Collectors.toList())
            )
            .price(cart.getPrice() + cart.getPayment().getPrice())
            .phone(phone)
            .email(email)
        .build();

        model.addAttribute("purchase", purchaseService.makePurchase(purchase));

        return "orderdone";

    }

}
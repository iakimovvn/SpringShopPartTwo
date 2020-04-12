package ru.geekbrains.supershop.services;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.geekbrains.supershop.exceptions.EntityNotFoundException;
import ru.geekbrains.supershop.persistence.entities.Product;
import ru.geekbrains.supershop.persistence.entities.Review;
import ru.geekbrains.supershop.persistence.entities.Shopuser;
import ru.geekbrains.supershop.persistence.entities.enums.ProductCategory;
import ru.geekbrains.supershop.persistence.entities.enums.Role;
import ru.geekbrains.supershop.persistence.pojo.ProductPojo;
import ru.geekbrains.supershop.persistence.pojo.ReviewPojo;
import ru.geekbrains.supershop.services.feign.clients.ShopFeignClient;

import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ShopuserService shopuserService;

    @Autowired
    private ProductService productService;

    @MockBean
    private ShopFeignClient shopFeignClient;

    private Shopuser shopuser;


    @Before
    public void init(){
        ProductPojo productPojo = ProductPojo.builder()
                .title("Пепси")
                .available(true)
                .category(ProductCategory.DRINK)
                .price(220.0)
                .description("")
                .build();
        productService.save(productPojo, null);

        shopuser = new Shopuser();
        shopuser.setPhone("123");
        shopuser.setPassword("123");
        shopuser.setFirstName("vladimir");
        shopuser.setLastName("vladimir");
        shopuser.setEmail("email@email.net");
        shopuser.setRole(Role.ROLE_ADMIN);
        shopuser = shopuserService.save(shopuser);

        Review review = new Review();
        review.setShopuser(shopuser);
        review.setCommentary("Commentary");
        review.setApproved(false);
        review.setProduct(productService.findAll(0).get(0));
        reviewService.save(review);
    }

    @Test
    public void getReviewsByProduct() {
        assertEquals(1, reviewService.getReviewsByProduct(
                productService.findAll(0)
                        .get(0)
                ).orElse(new ArrayList<>())
                .size()
        );
    }

    @Test
    public void getReviewsByShopuser() {
        assertEquals(1, reviewService.getReviewsByShopuser(shopuser)
                .orElse(new ArrayList<>())
                .size());
    }

    @Test
    public void moderate() throws EntityNotFoundException {
        reviewService.moderate(
                reviewService.getReviewsByShopuser(shopuser)
                        .orElse(new ArrayList<>())
                        .get(0).getId(),
                "approve"
        );

        assertTrue(reviewService.getReviewsByShopuser(shopuser)
                .orElse(new ArrayList<>())
                .get(0)
                .isApproved()
        );
    }

    @After
    public void clear(){
        reviewService.getAll().forEach(reviewService::delete);
        productService.findAll(null).forEach(productService::delete);
        shopuserService.delete(shopuser);
    }
}
package ru.geekbrains.supershop.services.mail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.geekbrains.supershop.persistence.entities.Purchase;
import ru.geekbrains.supershop.persistence.entities.Shopuser;
import ru.geekbrains.supershop.persistence.entities.enums.Role;
import ru.geekbrains.supershop.services.ShopuserService;

import java.util.ArrayList;

@SpringBootTest
@RunWith(SpringRunner.class)
class MailServiceTest {

    @Autowired
    private EmailService mailService;

    @Autowired
    private ShopuserService shopuserService;

    private Purchase purchase;


    @Before
    private void init(){
        Shopuser shopuser = new Shopuser();
        shopuser.setRole(Role.ROLE_ADMIN);
        shopuser.setEmail("asicsvolody@mail.ru");
        shopuser.setFirstName("Vladimir");
        shopuser.setLastName("Yakimov");
        shopuser.setPassword("1233");
        shopuser.setPhone("1233211");

        shopuser = shopuserService.save(shopuser);


        this.purchase = Purchase.builder()
                .shopuser(shopuser)
                .email("asicsvolody@mail.ru")
                .price(123.0)
                .phone("123456")
                .products(new ArrayList<>())
                .cartRecords(new ArrayList<>())
         .build();
    }

    @Test
    public void sendTest(){
    }





}
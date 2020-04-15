package ru.geekbrains.supershop.services.mail;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.geekbrains.supershop.persistence.entities.Purchase;
import ru.geekbrains.supershop.services.PurchaseService;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class MailServiceTest {

    @Autowired
    private MailService mailService;




    @Before
    private void init(){

    }





}
package ru.geekbrains.supershop.services.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ru.geekbrains.supershop.persistence.entities.Purchase;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendEmail(Purchase purchase){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(purchase.getEmail());
        msg.setSubject("Thanks for your order");
        msg.setText("Your order is: "+ purchase.getPrice());
        this.mailSender.send(msg);
    }


    public void sendHtmlEmail(Purchase purchase) throws MessagingException {

        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(purchase.getEmail());
        helper.setSubject("Order number: " + purchase.getId());
        helper.setText("<h1>Thank you for order<h1>"+
                "<p>Order price: " + purchase.getPrice() + "<p>"
        ,true);
        mailSender.send(msg);


    }




}

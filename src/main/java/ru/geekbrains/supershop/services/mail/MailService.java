package ru.geekbrains.supershop.services.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import ru.geekbrains.supershop.persistence.entities.Purchase;

/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private final MailSender mailSender;

    public void sendEmail(Purchase purchase){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(purchase.getEmail());
        msg.setSubject("Thanks for your order");
        msg.setText("Your order is: "+ purchase.getPrice());
        this.mailSender.send(msg);
    }


}

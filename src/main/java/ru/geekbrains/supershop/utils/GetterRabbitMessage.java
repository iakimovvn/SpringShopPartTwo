package ru.geekbrains.supershop.utils;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

@Component
public class GetterRabbitMessage {

    @RabbitListener(queues = "super-shop.queue")
    public void printMessage(String message){
        System.out.println("!!!!!!!!!!!!!!!"+message);
    }
}

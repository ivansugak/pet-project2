package com.tms.bank.rabbitMQ;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class VacanvyListener {

    @RabbitListener(queues = "myQueue")
    public void processMyQueue(String message){
        System.out.println("Receive from myQueue" + message);
    }
}

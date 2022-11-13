package com.tms.bank.rabbitMQ;

import com.tms.bank.models.Vacancy;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class VacanvyListener {

    private RabbitTemplate rabbit;

    public Vacancy receiveVacancy(){
        return  (Vacancy) rabbit.receiveAndConvert("myQueue");
    }

//    @RabbitListener(queues = "myQueue")
//    public void processMyQueue(String message){
//        System.out.println("Receive from myQueue" + message);
//    }

}

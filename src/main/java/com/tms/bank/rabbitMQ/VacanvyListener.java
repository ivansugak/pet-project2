package com.tms.bank.rabbitMQ;


import com.tms.bank.models.Vacancy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RabbitListener(queues = "rabbitmq.queue", id = "listener")
@Component
public class VacanvyListener {

    private static final Logger logger = LogManager.getLogger(VacanvyListener.class.toString());
    @RabbitHandler
    public void receiver(Vacancy vacancy) {
        logger.info("MenuOrder listener invoked - Consuming Message with MenuOrder Identifier : " + vacancy.getDescription());
    }
}

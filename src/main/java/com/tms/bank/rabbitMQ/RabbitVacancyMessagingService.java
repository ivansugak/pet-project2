package com.tms.bank.rabbitMQ;

import com.tms.bank.models.Vacancy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitVacancyMessagingService {

    private final AmqpTemplate rabbitTemplate;
    private final Queue queue;

    public RabbitVacancyMessagingService(AmqpTemplate rabbitTemplate, Queue queue) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    private static final Logger logger = LogManager.getLogger(RabbitVacancyMessagingService.class.toString());
    public void send(Vacancy vacancy) {
        rabbitTemplate.convertAndSend(queue.getName(), vacancy);
        logger.info("Sending Message to the Queue : " + vacancy.toString());
    }
}

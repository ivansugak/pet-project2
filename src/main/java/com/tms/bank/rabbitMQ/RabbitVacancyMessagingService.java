package com.tms.bank.rabbitMQ;

import com.tms.bank.models.Vacancy;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class RabbitVacancyMessagingService implements VacancyMessagingService{

    private final RabbitTemplate rabbit;

    @Autowired
    public RabbitVacancyMessagingService(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    @Override
    public void sendVacancy(Vacancy vacancy) {
        rabbit.convertAndSend("bank.vacancy", vacancy);

    }
}

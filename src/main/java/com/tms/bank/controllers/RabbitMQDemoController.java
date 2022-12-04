package com.tms.bank.controllers;

import com.tms.bank.models.Vacancy;
import com.tms.bank.rabbitMQ.RabbitVacancyMessagingService;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(value = "/rabbitmq")
public class RabbitMQDemoController {


    private final RabbitVacancyMessagingService rabbitVacancyMessagingService;

    public RabbitMQDemoController(RabbitVacancyMessagingService rabbitVacancyMessagingService) {
        this.rabbitVacancyMessagingService = rabbitVacancyMessagingService;
    }

    @PostMapping(value = "/sender")
    public String producer(@RequestBody Vacancy vacancy) {
        rabbitVacancyMessagingService.send(vacancy);
        return "Message sent to the RabbitMQ Queue Successfully";
    }
}

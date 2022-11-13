package com.tms.bank.rabbitMQ;

import com.tms.bank.models.Vacancy;

public interface VacancyMessagingService {
    void sendVacancy(Vacancy vacancy);
}

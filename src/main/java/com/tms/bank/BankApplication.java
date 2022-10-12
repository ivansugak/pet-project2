package com.tms.bank;

import com.tms.bank.enums.Role;
import com.tms.bank.models.User;
import com.tms.bank.models.Vacancy;
import com.tms.bank.repositories.UserRepository;
import com.tms.bank.repositories.VacancyRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankApplication.class, args);
    }

//    @Bean
//    public ApplicationRunner dataLoader(UserRepository repo, VacancyRepository repo1) {
//        return args -> {
//            repo.save(new User(23L, "ADMIN", "ADMINOV", 25, "Google", Role.ADMIN, "ADMIN_GENERAL", "$2a$10$ngKBn8lAHupZJD3qapOoeuoqkrRKRyag5eTp4FX4rUUdrdKHIU0vG"));
//            repo.save(new User(24L, "Anna", "Annov", 25, "Google", Role.USER, "Anna55", "$2a$10$B7zsImYWJbYe1C7Tu3TdQOHONUWbqpMOGgApZ6fvrjCek3euLPHEG"));
//
//
//            //noinspection OptionalGetWithoutIsPresent
//            repo1.save(new Vacancy(1, "Google", "Google", repo.getByLogin("Anna55").get()));
//            //noinspection OptionalGetWithoutIsPresent
//            repo1.save(new Vacancy(11, "Google", "Google", repo.getByLogin("Anna55").get()));
//        };
//    }
}

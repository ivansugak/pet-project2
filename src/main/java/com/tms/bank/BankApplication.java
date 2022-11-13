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

    @Bean
    public ApplicationRunner dataLoader(UserRepository repo, VacancyRepository repo1) {
        return args -> {
            repo1.deleteAll(repo1.findAll());
            repo.deleteAll(repo.findAll());

            repo.save(new User(23L, "ADMIN", "ADMINOV", 25, "Google", Role.ADMIN, "ADMIN_GENERAL", "$2a$10$alJjaoZz2I1Tkr7/.hlete6fWYGcvNIWb8uihY9l5Uh13idR7qzIS"));
            repo.save(new User(24L, "Anna", "Annov", 25, "Google", Role.USER, "Anna55", "$2a$10$iSLvEUyDUE/JHqGI16eK7eHiepQRg7BwMNlddoF2QjKgSfM6umZiW"));


            //noinspection OptionalGetWithoutIsPresent
            repo1.save(new Vacancy(1, "Google", "Google", repo.getByLogin("ADMIN_GENERAL").get()));
            //noinspection OptionalGetWithoutIsPresent
            repo1.save(new Vacancy(11, "Google", "Google", repo.getByLogin("ADMIN_GENERAL").get()));
            //noinspection OptionalGetWithoutIsPresent
            repo1.save(new Vacancy(12, "Java Developer (Junior)", "Solution", repo.getByLogin("ADMIN_GENERAL").get()));
            //noinspection OptionalGetWithoutIsPresent
            repo1.save(new Vacancy(13, "PHP Developer (Middle)", "IBA", repo.getByLogin("ADMIN_GENERAL").get()));
            //noinspection OptionalGetWithoutIsPresent
            repo1.save(new Vacancy(14, "Android Developer (Senior)", "Vizor", repo.getByLogin("ADMIN_GENERAL").get()));
            //noinspection OptionalGetWithoutIsPresent
            repo1.save(new Vacancy(15, "Kotlin Developer (Intern)", "IZO", repo.getByLogin("ADMIN_GENERAL").get()));
        };
    }
}

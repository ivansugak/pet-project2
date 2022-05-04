package com.tms.bank.repositories;

import com.tms.bank.models.User;
import com.tms.bank.models.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
}

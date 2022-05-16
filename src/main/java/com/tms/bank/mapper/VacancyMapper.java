package com.tms.bank.mapper;

import com.tms.bank.dto.VacancyDTO;
import com.tms.bank.models.Vacancy;

public class VacancyMapper {

    public static VacancyDTO mapVacancyToVacancyDTO(Vacancy vacancy){
        return VacancyDTO.builder()
                .vocation(vacancy.getVocationVacancy())
                .description(vacancy.getDescription())
                .build();
    }

    public static Vacancy mapVacancyDTOToVacancy(VacancyDTO vacancyDTO){
        return Vacancy.builder()
                .vocationVacancy(vacancyDTO.getVocation())
                .description(vacancyDTO.getDescription())
                .build();
    }
}

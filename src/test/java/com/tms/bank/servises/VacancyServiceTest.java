package com.tms.bank.servises;

import com.tms.bank.dto.VacancyDTO;
import com.tms.bank.models.Vacancy;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VacancyServiceTest {

    VacancyDTO testVacancy;

    @Autowired
    VacancyService vacancyService;

    @BeforeEach
    void init() {
        testVacancy = new VacancyDTO("PHP Developer", "description vocation");
    }

    @Test
    @Order(1)
    public void createVacancy() {
        vacancyService.createVacancy(testVacancy);
        Assertions.assertTrue(vacancyService.findVacancyByVocation(testVacancy.getVocation()));
    }

    @Test
    @Order(2)
    public void addListVacanvyForshowVacanciesDetail() {
       ArrayList<Vacancy> vacancies = vacancyService.addListVacanvyForshowVacanciesDetail(vacancyService.getIDByVocation("PHP Developer"));
        Assertions.assertEquals(1, vacancies.size());
    }

    @Test
    @Order(3)
    public void editVacancies() {
        Vacancy vacancy = vacancyService.editVacancies(vacancyService.getIDByVocation("PHP Developer"));
        vacancy.setVocationVacancy("PHP Developer EditTest");
        vacancyService.saveVacancy(vacancy);
        Assertions.assertEquals("PHP Developer EditTest", vacancy.getVocationVacancy());
    }

    @Test
    @Order(4)
    public void deleteVacancies() {
        if (vacancyService.existsVacancyById(vacancyService.getIDByVocation("PHP Developer EditTest"))) {
            vacancyService.deleteVacancies(vacancyService.getIDByVocation("PHP Developer EditTest"));
            Assertions.assertFalse(vacancyService.findVacancyByVocation("PHP Developer EditTest"));
        }
    }
}
package com.tms.bank.controllers;

import com.tms.bank.dto.VacancyDTO;
import com.tms.bank.models.Vacancy;
import com.tms.bank.servises.VacancyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@AllArgsConstructor
public class VacanciesController {

    private final VacancyService vacancyService;

    @GetMapping("/vacancies")
    public String showVacancies(Model model) {
        Iterable<Vacancy> vacancies = vacancyService.findByAllVacancy();
        model.addAttribute("vacancies", vacancies);
        return "vacancies";
    }

    @GetMapping("/vacancies/add")
    public String addVacancies() {
        return "vacancies-add";
    }

    @PostMapping("/vacancies/add")
    public String addPostVacancies(@RequestParam String vocation,
                                   @RequestParam String description
                                   ) {
        VacancyDTO vacancy = new VacancyDTO(vocation, description);
        vacancyService.createVacancy(vacancy);

        return "redirect:/vacancies";
    }

    @GetMapping("/vacancies/{id}")
    public String showVacanciesDetail(@PathVariable(value = "id") long id, Model model) {

        if (!vacancyService.existsVacancyById(id)) {
            return "redirect:/vacancies";
        }
        ArrayList<Vacancy> vacancies = vacancyService.addListVacanvyForshowVacanciesDetail(id);
        model.addAttribute("vacancy", vacancies);
        return "vacancies-detail";
    }

    @GetMapping("/vacancies/{id}/edit")
    public String editVacanciesDetail(@PathVariable(value = "id") long id, Model model) {

        if (!vacancyService.existsVacancyById(id)) {
            return "redirect:/vacancies";
        }

        ArrayList<Vacancy> vacancies = vacancyService.addListVacanvyForshowVacanciesDetail(id);
        model.addAttribute("vacancy", vacancies);
        return "vacancies-detail-edit";
    }

    @PostMapping("/vacancies/{id}/edit")
    public String editPostVacancies(@PathVariable(value = "id") long id,
                                    @RequestParam String vocation,
                                    @RequestParam String description) {

        Vacancy vacancy = vacancyService.editVacancies(id);
        vacancy.setVocationVacancy(vocation);
        vacancy.setDescription(description);
        vacancyService.saveVacancy(vacancy);

        return "redirect:/vacancies";
    }

    @PostMapping("/vacancies/{id}/remove")
    public String deletePostVacancies(@PathVariable(value = "id") long id) {
        vacancyService.deleteVacancies(id);
        return "redirect:/vacancies";
    }
}

package com.tms.bank.controllers;

import com.tms.bank.models.Company;
import com.tms.bank.models.Vacancy;
import com.tms.bank.repositories.CompanyRepository;
import com.tms.bank.repositories.VacancyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class VacanciesController {

    private VacancyRepository vacancyRepository;

    @GetMapping("/vacancies")
    public String showVacancies(Model model){
        Iterable<Vacancy> vacancies = vacancyRepository.findAll();
        model.addAttribute("vacancies", vacancies);
        return "vacancies";
    }

    @GetMapping("/vacancies/add")
    public String addVacancies(Model model){
        return "vacancies-add";
    }

    @PostMapping("/vacancies/add")
    public String addPostVacancies(@RequestParam String vocation,
                                   @RequestParam String description,
                                   Model model){
        Vacancy vacancy = new Vacancy(vocation, description);
        vacancyRepository.save(vacancy);
        return "redirect:/vacancies";
    }

    @GetMapping("/vacancies/{id}")
    public String showVacanciesDetail(@PathVariable(value = "id") long id, Model model){

        if (!vacancyRepository.existsById(id)){
            return "redirect:/vacancies";
        }
        Optional<Vacancy> vacancy = vacancyRepository.findById(id);
        ArrayList<Vacancy> vacancies = new ArrayList<>();
        vacancy.ifPresent(vacancies::add);
        model.addAttribute("vacancy", vacancies);
        return "vacancies-detail";
    }

    @GetMapping("/vacancies/{id}/edit")
    public String editVacanciesDetail(@PathVariable(value = "id") long id, Model model){

        if (!vacancyRepository.existsById(id)){
            return "redirect:/vacancies";
        }
        Optional<Vacancy> vacancy = vacancyRepository.findById(id);
        ArrayList<Vacancy> vacancies = new ArrayList<>();
        vacancy.ifPresent(vacancies::add);
        model.addAttribute("vacancy", vacancies);
        return "vacancies-detail-edit";
    }

    @PostMapping("/vacancies/{id}/edit")
    public String editPostVacancies(@PathVariable(value = "id") long id,
                                    @RequestParam String vocation,
                                    @RequestParam String description,
                                    Model model) {
        Vacancy vacancy = vacancyRepository.findById(id).orElseThrow();
        vacancy.setVocation(vocation);
        vacancy.setDescription(description);
        vacancyRepository.save(vacancy);
        return "redirect:/vacancies";
    }

    @PostMapping("/vacancies/{id}/remove")
    public String deletePostVacancies(@PathVariable(value = "id") long id,
                                    Model model) {
        Vacancy vacancy = vacancyRepository.findById(id).orElseThrow();
        vacancyRepository.delete(vacancy);
        return "redirect:/vacancies";
    }
}

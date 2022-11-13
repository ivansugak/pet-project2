package com.tms.bank.controllers;

import com.tms.bank.dto.VacancyDTO;
import com.tms.bank.models.User;
import com.tms.bank.models.Vacancy;
import com.tms.bank.servises.VacancyService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@Controller
//@AllArgsConstructor
public class VacanciesController {

//    Logger logger = LoggerFactory.getLogger(VacanciesController.class);

    private final VacancyService vacancyService;

    public VacanciesController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }
//    private int pageSize = 5;
//    private final AmqpTemplate template;

    @GetMapping("/vacancies")
    public String showVacancies(Model model) {  //page 184
        Pageable pageable = PageRequest.of(0, 5);
        Iterable<Vacancy> vacancies = vacancyService.findByAllVacancy(pageable);
//        Iterable<Vacancy> vacancies = vacancyService.findByAllVacancy();
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

//    @PostMapping("/rabMQ")
//    public ResponceEntity rabMQ(@RequestBody String message){
//        template.convertAndSend("myQueue", message);
//        return ResponceEntity.ok("Success rabMQ to queue");
//    }
}

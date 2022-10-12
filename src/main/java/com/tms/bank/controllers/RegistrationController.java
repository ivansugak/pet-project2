package com.tms.bank.controllers;

import com.tms.bank.dto.UserDTO;
import com.tms.bank.servises.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
@AllArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @PostMapping()
    public String create(@Valid @RequestParam("username") String username,
                         @Valid @RequestParam("password") String password,
                         @Valid @RequestParam("firstname") String firstname,
                         @Valid @RequestParam("lastname") String lastname,
                         @Valid @RequestParam("age") int age,
                         @RequestParam("vocation") String vocation,
                         Errors errors) {

        if (errors.hasErrors()){
            return "registration";
        }

        UserDTO userDTO = UserDTO.builder()
                .login(username)
                .password(password)
                .firstName(firstname)
                .lastName(lastname)
                .age(age)
                .vocation(vocation)
                .build();

        if (userService.getUserByLogin(username)) {
            userService.createUser(userDTO);
            return "/vacancies";
        }
        else {
            return "errorAuth";
        }
    }
}

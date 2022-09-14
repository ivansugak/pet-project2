package com.tms.bank.controllers;

import com.tms.bank.dto.UserDTO;
import com.tms.bank.servises.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/registration")
@AllArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @PostMapping()
    public String create(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("firstname") String firstname,
                         @RequestParam("lastname") String lastname,
                         @RequestParam("age") int age,
                         @RequestParam("vocation") String vocation) {

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

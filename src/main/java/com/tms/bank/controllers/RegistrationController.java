package com.tms.bank.controllers;

import com.tms.bank.dto.UserDTO;
import com.tms.bank.servises.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/registration")
@AllArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @PostMapping()
    public String create(@RequestBody UserDTO userDTO) {

//        UserDTO userDTO = UserDTO.builder()
//                .login(username)
//                .password(password)
//                .firstName(firstname)
//                .lastName(lastname)
//                .age(age)
//                .vocation(vocation)
//                .build();

        if (userService.getUserByLogin(userDTO.getLogin())) {
            userService.createUser(userDTO);
            return "/vacancies";
        }
        else {
            return "errorAuth";
        }
    }
}

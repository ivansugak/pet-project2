package com.tms.bank.controllers;

import com.tms.bank.mapper.UserMapper;
import com.tms.bank.models.User;
import com.tms.bank.servises.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class UsersController {

    private final UserService userService;

    @GetMapping("/user/{id}")
    public String showUser(@PathVariable(value = "id") long id, Model model){
        if (!userService.existsById(id)){
            return "redirect:/";
        }
        Optional<User> user = Optional.ofNullable(UserMapper.mapToEntity(userService.getUserById(id)));
        model.addAttribute("user", user);
        return "user";
    }

    @PostMapping("/user/{id}/remove")
    public String deleteUser(@PathVariable(value = "id") long id,
                             Model model){
        userService.delete(id);
        return "redirect:/";
    }

}

package com.tms.bank.controllers;

import com.tms.bank.models.User;
import com.tms.bank.servises.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping("/admin")
    public String usersList(Model model){
        Iterable<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "admin";
    }

    @PostMapping("/admin/{id}/remove")
    public String deleteUser(@PathVariable(value = "id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}

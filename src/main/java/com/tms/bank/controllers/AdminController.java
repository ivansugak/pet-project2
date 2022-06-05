package com.tms.bank.controllers;

import com.tms.bank.servises.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping("/admin")
    public String usersList(Model model){
        model.addAttribute("allUsers", userService.getUsers());
        return "admin";
    }

    public String deleteUser(@RequestParam(required = true, defaultValue = "" ) Long userId,
                             @RequestParam(required = true, defaultValue = "" ) String action,
                             Model model){
        if(action.equals("delete")){
            userService.delete(userId);
        }
        return "redirect:/admin";
    }

}

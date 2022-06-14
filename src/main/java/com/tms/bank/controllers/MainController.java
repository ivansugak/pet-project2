package com.tms.bank.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "home");
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "about");
        return "about";
    }

    @GetMapping("/help")
    public String help(Model model) {
        model.addAttribute("title", "help");
        return "help";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("title", "registration");
        return "registration";
    }

    @GetMapping("/auth")
    public String auth(Model model) {
        model.addAttribute("title", "auth");
        return "auth";
    }

    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }

}

package com.tms.bank.controllers;

import com.tms.bank.exception.UserException;
import com.tms.bank.payload.AuthRequest;
import com.tms.bank.servises.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CustomUserDetailsService userDetailsService;

    @PostMapping
    public UserDetails login (@RequestBody AuthRequest authRequest) throws UserException {
        return userDetailsService.loadUserByUsername(authRequest.getUsername());
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            request.getSession().invalidate();
        }
        return "redirect:/";
    }

}

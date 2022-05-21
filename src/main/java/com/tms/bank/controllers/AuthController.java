package com.tms.bank.controllers;

import com.tms.bank.exception.UserException;
import com.tms.bank.payload.AuthRequest;
import com.tms.bank.servises.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CustomUserDetailsService userDetailsService;

    public UserDetails login (@RequestBody AuthRequest authRequest) throws UserException {
        return userDetailsService.loadUserByUsername(authRequest.getUsername());
    }

}
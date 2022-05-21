package com.tms.bank.mapper;


import com.tms.bank.models.User;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsMapper {

    public static UserDetails mapToCustomUser(User user){

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getAuthcredential().getLogin())
                .password(user.getAuthcredential().getPassword())
                .build();
    }
}

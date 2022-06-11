package com.tms.bank.dto;

import com.tms.bank.enums.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private String firstName;
    private String lastName;
    private int age;
    private Role role;
    private String vocation;
    private String login;
    private String password;
}

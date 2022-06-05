package com.tms.bank.payload;

import com.tms.bank.enums.Role;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegistrRequest {
    private String firstName;
    private String lastName;
    private int age;
    private String vocation;
    private Role role = Role.USER;
    private String login;
    private String password;
}

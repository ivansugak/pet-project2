package com.tms.bank.dto;

//import com.tms.bank.enums.Role;
import com.tms.bank.enums.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private int age;
    private String vocation;
    private Role role = Role.USER;
//    private Role role;
//    private String role;
}

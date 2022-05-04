package com.tms.bank.dto;

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
    private String vocation;
    private String role;
}

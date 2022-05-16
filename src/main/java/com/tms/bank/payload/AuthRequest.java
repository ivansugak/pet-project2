package com.tms.bank.payload;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthRequest {
    private String username;
    private String password;

}

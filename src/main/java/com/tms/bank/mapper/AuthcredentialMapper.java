//package com.tms.bank.mapper;
//
//import com.tms.bank.dto.UserDTO;
//import com.tms.bank.models.Authcredential;
//
//public class AuthcredentialMapper {
//
//    public static UserDTO mapToDTO(Authcredential authcredential){
//        return UserDTO.builder()
//                .login(authcredential.getLogin())
//                .password(authcredential.getPassword())
//                .build();
//    }
//
//    public static Authcredential mapToEntity(UserDTO userDTO){
//        return Authcredential.builder()
//                .login(userDTO.getLogin())
//                .password(userDTO.getPassword())
//                .build();
//    }
//}

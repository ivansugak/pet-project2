package com.tms.bank.mapper;

import com.tms.bank.dto.UserDTO;
import com.tms.bank.models.User;


public class UserMapper {

    public static UserDTO mapToDTO(User user){
        return UserDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .vocation(user.getVocation())
                .role(user.getRole())
                .build();
    }

    public static User mapToEntity(UserDTO userDTO){
        return User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .age(userDTO.getAge())
                .vocation(userDTO.getVocation())
                .role(userDTO.getRole())
                .build();
    }
}

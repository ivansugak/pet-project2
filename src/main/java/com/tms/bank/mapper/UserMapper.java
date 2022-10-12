package com.tms.bank.mapper;

import com.tms.bank.dto.UserDTO;
import com.tms.bank.enums.Role;
import com.tms.bank.models.User;
import com.tms.bank.servises.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class UserMapper {

    static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static UserDTO mapToDTO(User user){
        return UserDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .vocation(user.getVocation())
                .role(user.getRole())
                .login(user.getLogin())
                .password(user.getPassword())
                .build();
    }

    public static User mapToEntity(UserDTO userDTO){
        return User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .age(userDTO.getAge())
                .vocation(userDTO.getVocation())
                .role(userDTO.getRole())
                .login(userDTO.getLogin())
                .password(userDTO.getPassword())
                .build();
    }

    public static User mapToEntityForServices(UserDTO userDTO){
        return User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .age(userDTO.getAge())
                .vocation(userDTO.getVocation())
                .role(Role.USER)
                .login(userDTO.getLogin())
                .password(bCryptPasswordEncoder.encode(userDTO.getPassword()))
                .build();
    }
}

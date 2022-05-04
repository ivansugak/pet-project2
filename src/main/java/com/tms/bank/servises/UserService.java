package com.tms.bank.servises;

import com.tms.bank.dto.UserDTO;
import com.tms.bank.mapper.UserMapper;
import com.tms.bank.models.User;
import com.tms.bank.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public UserDTO getUserById(Long id){
        if(userRepository.findById(id).isPresent()){
            return UserMapper.mapToDTO(userRepository.findById(id).get());
        }
        return null;
    }

    public UserDTO createUser (UserDTO userDTO){
        return UserMapper.mapToDTO(userRepository.save(UserMapper.mapToEntity(userDTO)));
    }

    public UserDTO updateUser (Long id, UserDTO userDTO){
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()){
            User userEntity = user.get();
            userEntity.setFirstName(userDTO.getFirstName());
            userEntity.setLastName(userDTO.getLastName());
            userEntity.setAge(userDTO.getAge());
            userEntity.setVocation(userDTO.getVocation());
            userEntity.setRole(userDTO.getRole());

            return UserMapper.mapToDTO(userRepository.save(userEntity));
        } else {
            throw new EntityNotFoundException("User was not found in database");
        }
    }

}

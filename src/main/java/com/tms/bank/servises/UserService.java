package com.tms.bank.servises;

import com.tms.bank.dto.UserDTO;
import com.tms.bank.enums.Role;
import com.tms.bank.mapper.UserMapper;
import com.tms.bank.models.User;
import com.tms.bank.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public UserDTO getUserById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            return UserMapper.mapToDTO(userRepository.findById(id).get());
        }
        return null;
    }

    public UserDTO createUser(UserDTO userDTO) {

        userRepository.save(User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .age(userDTO.getAge())
                .role(Role.USER)
                .vocation(userDTO.getVocation())
                .login(userDTO.getLogin())
                .password(bCryptPasswordEncoder.encode(userDTO.getPassword()))
                .build());

        return userDTO;
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        Optional<User> user = userRepository.findById(id);
        UserDTO newUserDTO;

        if (user.isPresent()) {
            User userEntity = user.get();
            userEntity.setFirstName(userDTO.getFirstName());
            userEntity.setLastName(userDTO.getLastName());
            userEntity.setAge(userDTO.getAge());
            userEntity.setVocation(userDTO.getVocation());
            userEntity.setLogin(userDTO.getLogin());
            userEntity.setPassword(userDTO.getPassword());

            newUserDTO = UserMapper.mapToDTO(userRepository.save(userEntity));
            return newUserDTO;
        } else {
            throw new EntityNotFoundException("User was not found in database");
        }
    }

    public String delete(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.delete(userRepository.findById(id).get());
        }
        return "User " + id + " delete";
    }

    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    public boolean getUserByLogin(String login) {
        if (userRepository.getByLogin(login).isPresent()) {
            return false;
        }
        return true;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Long getIDByLogin(String login){
        Long id = null;
        if (userRepository.getByLogin(login).isPresent()) {
            id = userRepository.getByLogin(login).get().getId();
        }
        return id;
    }
}

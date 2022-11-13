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

    public UserDTO createUser(UserDTO userDTO) { //рабочий код

        userRepository.save(User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .age(userDTO.getAge())
                .vocation(userDTO.getVocation())
                .role(Role.USER)
                .login(userDTO.getLogin())
                .password(bCryptPasswordEncoder.encode(userDTO.getPassword()))
                .build());

        return userDTO;
    }

//    public void createUser(UserDTO userDTO) { //рефакт код
//
//        userRepository.save(UserMapper.mapToEntityForServices(userDTO));
//    }

//    public UserDTO updateUser(Long id, UserDTO userDTO) { //рабочий код
//        Optional<User> user = userRepository.findById(id);
//        UserDTO newUserDTO;
//
//        if (user.isPresent()) {
//            User userEntity = user.get();
//            userEntity.setFirstName(userDTO.getFirstName());
//            userEntity.setLastName(userDTO.getLastName());
//            userEntity.setAge(userDTO.getAge());
//            userEntity.setVocation(userDTO.getVocation());
//            userEntity.setLogin(userDTO.getLogin());
//            userEntity.setPassword(userDTO.getPassword());
//
//            newUserDTO = UserMapper.mapToDTO(userRepository.save(userEntity));
//            return newUserDTO;
//        } else {
//            throw new EntityNotFoundException("User was not found in database");
//        }
//    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {  //билдер
        Optional<User> user = userRepository.findById(id);
        UserDTO newUserDTO;

        if (user.isEmpty()) {
            throw new EntityNotFoundException("User was not found in database");
        }
        User userEntity = user.get();
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setAge(userDTO.getAge());
        userEntity.setVocation(userDTO.getVocation());
        userEntity.setLogin(userDTO.getLogin());
        userEntity.setPassword(userDTO.getPassword());

        newUserDTO = UserMapper.mapToDTO(userRepository.save(userEntity));
        return newUserDTO;
    }

    public void deleteUser(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("User was not found in database");
        }
        userRepository.delete(userRepository.findById(id).get());
    }

    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    public boolean getUserByLogin(String login) {
        return userRepository.getByLogin(login).isEmpty();
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

//    public Long getIdByLogin(String login){ //рабочий код
//        Long id = null;
//        if (userRepository.getByLogin(login).isPresent()) {
//            id = userRepository.getByLogin(login).get().getId();
//        }
//        return id;
//    }

    public Long getIdByLogin(String login) {
//
//        User user = userRepository.getByLogin(login).orElseThrow(RuntimeException::new);
//
//        return user.getId();

        return userRepository.getByLogin(login).isPresent() ? userRepository.getByLogin(login).get().getId() : null;


    }
}

package com.tms.bank.servises;

import com.tms.bank.dto.UserDTO;
import com.tms.bank.mapper.AuthcredentialMapper;
import com.tms.bank.mapper.UserMapper;
import com.tms.bank.models.Authcredential;
import com.tms.bank.models.User;
import com.tms.bank.models.Vacancy;
import com.tms.bank.repositories.AuthcredentialRepository;
import com.tms.bank.repositories.UserRepository;
import com.tms.bank.repositories.VacancyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthcredentialRepository authcredentialRepository;

    public UserDTO getUserById(Long id){
        if(userRepository.findById(id).isPresent()){
            return UserMapper.mapToDTO(userRepository.findById(id).get());
        }
        return null;
    }

    public UserDTO createUser (UserDTO userDTO){ //to do companyRep + userDTO (companyDTO+authDTO)
        UserDTO newUserDTO = UserMapper.mapToDTO(userRepository.save(UserMapper.mapToEntity(userDTO)));
        AuthcredentialMapper.mapToDTO(authcredentialRepository.save(AuthcredentialMapper.mapToEntity(userDTO)));

        return newUserDTO;
    }

    public UserDTO updateUser (Long id, UserDTO userDTO){
        Optional<User> user = userRepository.findById(id);
        UserDTO newUserDTO;

        if (user.isPresent()){
            User userEntity = user.get();
            Authcredential authcredentialEntity = authcredentialRepository.getById(id);

            userEntity.setFirstName(userDTO.getFirstName());
            userEntity.setLastName(userDTO.getLastName());
            userEntity.setAge(userDTO.getAge());
            userEntity.setVocation(userDTO.getVocation());
            authcredentialEntity.setLogin(userDTO.getLogin());
            authcredentialEntity.setPassword(userDTO.getPassword());

            newUserDTO = UserMapper.mapToDTO(userRepository.save(userEntity));
            AuthcredentialMapper.mapToDTO(authcredentialRepository.save(AuthcredentialMapper.mapToEntity(userDTO)));
            return newUserDTO;
        } else {
            throw new EntityNotFoundException("User was not found in database");
        }
    }

    public String delete(Long id){
        if(userRepository.findById(id).isPresent()){
            userRepository.delete(userRepository.findById(id).get());
        }
        return "User " + id + " delete";
    }

//    public List<Vacancy> findByAllVacancy(){
//        return vacancyRepository.findAll();
//    }

    public boolean existsById(Long id){
        return userRepository.existsById(id);
    }


}

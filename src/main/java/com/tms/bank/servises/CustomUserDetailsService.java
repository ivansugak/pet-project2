package com.tms.bank.servises;

import com.tms.bank.exception.UserException;
import com.tms.bank.mapper.UserDetailsMapper;
import com.tms.bank.models.User;
import com.tms.bank.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        User user = userRepository.getByLogin(username)
                .orElseThrow(() -> new UserException("User was not found!"));
//        Optional<User> user = Optional.ofNullable(userRepository.getByLogin(username)
//                .orElseThrow(() -> new UserException("User was not found!"))); //перед ретурном просто проверь опшонал, что имел ввиду?

        return UserDetailsMapper.mapToCustomUser(user);
    }
}

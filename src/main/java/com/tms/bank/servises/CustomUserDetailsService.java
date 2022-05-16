package com.tms.bank.servises;

import com.tms.bank.exception.UserException;
import com.tms.bank.mapper.UserDetailsMapper;
import com.tms.bank.models.Authcredential;
import com.tms.bank.models.User;
import com.tms.bank.repositories.AuthcredentialRepository;
import com.tms.bank.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
//    private final AuthcredentialRepository authcredentialRepository;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) {
//        Authcredential authcredential = authcredentialRepository.findByLogin(username)
//                .orElseThrow(()->new UserException("User was not found!"));

        User user = userRepository.findUserByAuthcredential_Login(username)
                .orElseThrow(()->new UserException("User was not found!"));

        return UserDetailsMapper.mapToCustomUser(user);
    }
}

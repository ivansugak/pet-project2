package com.tms.bank.servises;

import com.tms.bank.exception.UserException;
import com.tms.bank.mapper.UserDetailsMapper;
import com.tms.bank.models.User;
import com.tms.bank.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        User user = null;
        try {
            user = userRepository.getByLogin(username)
                    .orElseThrow(()->new UserException("User was not found!"));
        } catch (UserException e) {
            e.printStackTrace();
        }

        return UserDetailsMapper.mapToCustomUser(user);

    }
}

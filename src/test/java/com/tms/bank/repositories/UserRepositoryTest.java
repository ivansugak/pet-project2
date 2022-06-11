package com.tms.bank.repositories;

import com.tms.bank.enums.Role;
import com.tms.bank.models.User;
import org.junit.jupiter.api.*;
import java.util.Optional;


class UserRepositoryTest {

    UserRepository userRepository;
    User expectedUser;

    @BeforeEach
    void init() {
        expectedUser = new User();
        expectedUser.setFirstName("Anna");
        expectedUser.setLastName("Annov");
        expectedUser.setAge(25);
        expectedUser.setVocation("YANDEX-JUNIOR");
        expectedUser.setRole(Role.USER);
        expectedUser.setLogin("Anna");
        expectedUser.setPassword("$2a$12$ktSGWbkwm8pn4lQduwXYTOqp3I0Q3SUFDuj1El1Iw2WRSBhfofASG");
    }

    @Test
    @DisplayName("Testing search user which exists in database")
    @Tag("Repository-TEST")
    void findUserByLogin() {
        Optional<User> actualUser = userRepository.findUserByLogin("Anna");
        Assertions.assertEquals(expectedUser,actualUser);
    }
}
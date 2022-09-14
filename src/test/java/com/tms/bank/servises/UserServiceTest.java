package com.tms.bank.servises;

import com.tms.bank.dto.UserDTO;
import com.tms.bank.enums.Role;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {

    UserDTO testUser;
    Long id = 0L;

    @Autowired
    UserService userService;

    @BeforeEach
    void init() {
        testUser = new UserDTO("FirstName", "LastName", 20, Role.USER,
                "Java Developer", "userLogin", "userPassword");
    }

    @Test
    @Order(1)
    @DisplayName("Testing saving user in database")
    public void createUser() {
        userService.createUser(testUser);
        Assertions.assertFalse(userService.getUserByLogin("userLogin"));
    }

    @ParameterizedTest
    @Order(2)
    @DisplayName("Testing search user in database")
    @ValueSource(strings = "userLogin")
    public void getUserById(String login) {
        Assertions.assertFalse(userService.getUserByLogin(login));
    }

    @Test
    @Order(3)
    @DisplayName("Testing update user in database")
    public void updateUser() {
        id = userService.getIDByLogin("userLogin");
        testUser.setLogin("userLoginUpdate");
        userService.updateUser(id, testUser);
        Assertions.assertEquals("userLoginUpdate", userService.getUserById(id).getLogin());
    }

    @Test
    @Order(4)
    @DisplayName("Testing search user which exists in database")
    void existsById() {
        long idForExistsByIdMethod = userService.getIDByLogin("userLoginUpdate");
        Assertions.assertTrue(idForExistsByIdMethod > 0);
    }

    @Test
    @Order(5)
    @DisplayName("Testing delete user from database")
    public void delete() {
        long idForDeleteMethod = userService.getIDByLogin("userLoginUpdate");
        if (userService.existsById(idForDeleteMethod)) {
            userService.delete(idForDeleteMethod);
            Assertions.assertTrue(userService.getUserByLogin("userLoginUpdate"));
        }
    }
}
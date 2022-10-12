package com.tms.bank.servises;

import com.tms.bank.dto.UserDTO;
import com.tms.bank.enums.Role;
import com.tms.bank.mapper.UserMapper;
import com.tms.bank.models.User;
import com.tms.bank.repositories.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
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
        id = userService.getIdByLogin("userLogin");
        testUser.setLogin("userLoginUpdate");
        userService.updateUser(id, testUser);
        Assertions.assertEquals("userLoginUpdate", userService.getUserById(id).getLogin());
    }

    @Test
    @Order(4)
    @DisplayName("Testing search user which exists in database")
    void existsUserById() {
        long idForExistsByIdMethod = userService.getIdByLogin("userLoginUpdate");
        Assertions.assertTrue(idForExistsByIdMethod > 0);
    }

    @Test
    @Order(5)
    @DisplayName("Testing delete user from database")
    public void deleteUser() {
        long idForDeleteMethod = userService.getIdByLogin("userLoginUpdate");
        if (userService.existsById(idForDeleteMethod)) {
            userService.deleteUser(idForDeleteMethod);
            Assertions.assertTrue(userService.getUserByLogin("userLoginUpdate"));
        }
    }

    @InjectMocks
    private UserService userServiceTest;

    @Mock
    private UserRepository userRepositoryTest;


    @Test
    @DisplayName("Testing search user which exists in database")
    public void getUserByIdTest() {
        when(userRepositoryTest.getByLogin("Jorik")).thenReturn(Optional.of(new User(35, "Jorik", "Jorikov", 27, "PHP Developer", Role.USER, "Jorik85", "Jorik1985")));

        boolean emp = userServiceTest.getUserByLogin("Jorik");

        Assertions.assertTrue(emp);
    }

    @Test
    @DisplayName("Testing saving user in database")
    public void createUserTest() {

        userServiceTest.createUser(new UserDTO());

        verify(userRepositoryTest, times(1)).save(any(User.class));
        verifyNoMoreInteractions(userRepositoryTest);
    }

    @Test
    @DisplayName("Testing update user in database")
    public void updateUserTest() {

        userServiceTest.updateUser(anyLong(), new UserDTO());

        verify(userRepositoryTest, times(1)).save(any(User.class));
        verifyNoMoreInteractions(userRepositoryTest);
    }

    @Test
    @DisplayName("Testing delete user from database")
    public void deleteUserTest() {

        userServiceTest.deleteUser(anyLong());
        verify(userRepositoryTest, times(1)).deleteById(anyLong());
        verifyNoMoreInteractions(userRepositoryTest);
    }

    @Test
    @DisplayName("Testing search user which exists in database")
    public void existsUserByIdTest() {

        boolean check = userServiceTest.existsById(anyLong());

        Assertions.assertTrue(check);
        verify(userRepositoryTest, times(1)).existsById(anyLong());
        verifyNoMoreInteractions(userRepositoryTest);
    }

    @Test
    @DisplayName("Testing search user by login")
    public void getUserByLoginTest() {

        boolean check = userServiceTest.getUserByLogin(anyString());

        Assertions.assertTrue(check);
        verify(userRepositoryTest, times(1)).getByLogin(anyString());
        verifyNoMoreInteractions(userRepositoryTest);
    }

    @Test
    @DisplayName("Testing get all users")
    public void getUsersTest() {
        User user1 = new User(35L, "Jorik", "Jorikov", 27, "PHP Developer", Role.USER, "Jorik85", "Jorik1985");
        User user2 = new User(36L, "Forik", "Forikov", 27, "PHP Developer", Role.USER, "Forik85", "Forik1985");

        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);

        when(userRepositoryTest.findAll()).thenReturn(list);

        List<User> empList = userServiceTest.getUsers();

        assertEquals(2, empList.size());
        verify(userRepositoryTest, times(1)).findAll();
    }
}
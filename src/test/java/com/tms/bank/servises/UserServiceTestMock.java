package com.tms.bank.servises;

import com.tms.bank.dto.UserDTO;
import com.tms.bank.enums.Role;
import com.tms.bank.models.User;
import com.tms.bank.repositories.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTestMock {

    @InjectMocks
    private UserService userServiceTest;

    @Mock
    private UserRepository userRepositoryTest;

    @Test
    @DisplayName("Testing with MOCKITO search user which exists in database")
    public void getUserByIdTest() {
        when(userRepositoryTest.getByLogin("Jorik")).thenReturn(Optional.of(new User(35, "Jorik", "Jorikov", 27, "PHP Developer", Role.USER, "Jorik85", "Jorik1985")));

        boolean emp = userServiceTest.getUserByLogin("Jorik");

        Assertions.assertTrue(emp);
    }

    @Test
    @DisplayName("Testing with MOCKITO saving user in database")
    public void createUserTest() {

        userServiceTest.createUser(new UserDTO("Jorik", "Jorikov", 27, Role.USER, "PHP Developer", "Jorik85", "Jorik1985"));

        verify(userRepositoryTest, times(1)).save(any(User.class));
        verifyNoMoreInteractions(userRepositoryTest);
    }

    @Test
    @DisplayName("Testing with MOCKITO update user in database")
    public void updateUserTest() {

        userServiceTest.updateUser(anyLong(), new UserDTO());

        verify(userRepositoryTest, times(1)).save(any(User.class));
        verifyNoMoreInteractions(userRepositoryTest);
    }

    @Test
    @DisplayName("Testing with MOCKITO delete user from database")
    public void deleteUserTest() {

        userServiceTest.deleteUser(anyLong());
        verify(userRepositoryTest, times(1)).deleteById(anyLong());
        verifyNoMoreInteractions(userRepositoryTest);
    }

    @Test
    @DisplayName("Testing with MOCKITO search user which exists in database")
    public void existsUserByIdTest() {

        boolean check = userServiceTest.existsById(anyLong());

        Assertions.assertTrue(check);
        verify(userRepositoryTest, times(1)).existsById(anyLong());
        verifyNoMoreInteractions(userRepositoryTest);
    }

    @Test
    @DisplayName("Testing search user by login with MOCKITO")
    public void getUserByLoginTest() {
        when(userRepositoryTest.getByLogin(anyString())).thenReturn(Optional.of(new User()));
        boolean check = userServiceTest.getUserByLogin("test"); //падает потому что логин=0, и поэтому метод гетбайлоги не вызывает, хотя должен 1 раз по условия теста?

        Assertions.assertTrue(check);
        verify(userRepositoryTest, times(1)).getByLogin(anyString());
        verifyNoMoreInteractions(userRepositoryTest);
    }

    @Test
    @DisplayName("Testing get all users with MOCKITO")
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

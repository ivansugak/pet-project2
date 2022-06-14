//package com.tms.bank.servises;
//
//import com.tms.bank.enums.Role;
//import com.tms.bank.models.User;
//import com.tms.bank.repositories.UserRepository;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;
//import java.util.List;
//import java.util.Optional;
//
//class UserServiceTest {
//
//    User expectedUser;
//    User expectedUser2;
//    UserRepository userRepository;
//    List<User> userList;
//
//    @BeforeEach
//    void init() {
//        expectedUser = new User();
//        expectedUser.setFirstName("Anna");
//        expectedUser.setLastName("Annov");
//        expectedUser.setAge(39);
//        expectedUser.setVocation("YANDEX-JUNIOR");
//        expectedUser.setRole(Role.USER);
//        expectedUser.setLogin("Anna");
//        expectedUser.setPassword("$2a$10$IXLdV7q4nA7XRWPVCJxr3uqufChn7VnAN/UiNJjIa5Z1H1FkFY.oG");
//
//        expectedUser2 = new User();
//        expectedUser2.setFirstName("ADMIN");
//        expectedUser2.setLastName("ADMIN");
//        expectedUser2.setAge(25);
//        expectedUser2.setVocation("YANDEX-JUNIOR");
//        expectedUser2.setRole(Role.ADMIN);
//        expectedUser2.setLogin("ADMIN");
//        expectedUser2.setPassword("$2a$10$ngKBn8lAHupZJD3qapOoeuoqkrRKRyag5eTp4FX4rUUdrdKHIU0vG");
//
//        userList.add(expectedUser);
//        userList.add(expectedUser2);
//
//    }
//
//    @ParameterizedTest
//    @DisplayName("Testing search user in database")
//    @ValueSource(longs = {2})
//    void getUserById(long id) {
//        Optional<User> actualUser = userRepository.findById(id);
//        Assertions.assertEquals(expectedUser, actualUser);
//    }
//
//    @Test
//    @DisplayName("Testing saving user in database")
//    void createUser() {
//        User actualUser = userRepository.save(expectedUser);
//        Assertions.assertEquals(expectedUser, actualUser);
//    }
//
//    @ParameterizedTest
//    @DisplayName("Testing update user in database")
//    @ValueSource(longs = {1})
//    void updateUser(Long id) {
//        Optional<User> user = userRepository.findById(id);
//        User userEntity = null;
//
//        if (user.isPresent()) {
//            userEntity = user.get();
//            userEntity.setFirstName(expectedUser.getFirstName());
//            userEntity.setLastName(expectedUser.getLastName());
//            userEntity.setAge(expectedUser.getAge());
//            userEntity.setVocation(expectedUser.getVocation());
//            userEntity.setLogin(expectedUser.getLogin());
//            userEntity.setPassword(expectedUser.getPassword());
//        }
//        Assertions.assertEquals(expectedUser, userEntity);
//    }
//
//    @ParameterizedTest
//    @DisplayName("Testing delete user from database")
//    @ValueSource(longs = {2})
//    void delete(long id) {
//        if (userRepository.findById(id).isPresent()) {
//            userRepository.delete(userRepository.findById(id).get());
//            Assertions.assertNull(expectedUser);
//        }
//    }
//
//    @ParameterizedTest
//    @DisplayName("Testing search user which exists in database")
//    @ValueSource(longs = {2})
//    void existsById(long id) {
//        Optional<User> actualUser = userRepository.findById(id);
//        Assertions.assertEquals(expectedUser, actualUser);
//    }
//
//    @ParameterizedTest
//    @DisplayName("Testing search user by login in database")
//    @ValueSource(strings = "Anna")
//    void getUserByLogin(String login) {
//        Optional<User> actualUser = userRepository.findByLogin(login);
//        Assertions.assertEquals(expectedUser, actualUser);
//    }
//
//    @Test
//    @DisplayName("Testing search all users which exists in database")
//    void getUsers() {
//        List<User> users = userRepository.findAll();
//        Assertions.assertEquals(userList, users);
//    }
//}
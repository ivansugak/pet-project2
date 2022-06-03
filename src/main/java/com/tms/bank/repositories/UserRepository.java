package com.tms.bank.repositories;

import com.tms.bank.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("select u from User u where u.emailAddress = ?1")
    Optional<User> findUserByAuthcredentialLogin(String login);
//    Optional<User> findUserByAuthcredential_Login(String login);
}

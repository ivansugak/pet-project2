package com.tms.bank.repositories;

import com.tms.bank.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import java.util.Optional;

//@Repository
//@EnableJpaRepositories("com.tms.bank.repositories")
//public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findByLogin(String login);
//}

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getByLogin(String login);
}


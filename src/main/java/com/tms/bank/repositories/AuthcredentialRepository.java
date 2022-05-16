package com.tms.bank.repositories;

import com.tms.bank.models.Authcredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthcredentialRepository extends JpaRepository<Authcredential, Long> {
    Optional<Authcredential> findByLogin(String login);
}

package com.stepa7.bank.repository;

import com.stepa7.bank.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findById(UUID id);

    boolean existsAccountById(UUID id);
}

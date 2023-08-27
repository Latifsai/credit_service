package com.example.credit_service_project.repositories;

import com.example.credit_service_project.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByIdOrAccountNumber(UUID uuid, String accountNumber);

}

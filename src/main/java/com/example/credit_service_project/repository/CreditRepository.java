package com.example.credit_service_project.repository;

import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Credit;
import com.example.credit_service_project.entity.enums.CreditStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CreditRepository extends JpaRepository<Credit, UUID> {

        List<Credit> findByAccountAndCreditStatus(Account account, CreditStatus status);
}

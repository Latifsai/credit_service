package com.example.credit_service_project.repositories;

import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OperationRepository extends JpaRepository<Operation, UUID> {
    List<Operation> findAllByAccount(Account account);

}

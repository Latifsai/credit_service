package com.example.credit_service_project.repository;

import com.example.credit_service_project.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OperationRepository extends JpaRepository<Operation, UUID> {
    Optional<Operation> findByIdAndDebit(UUID id, boolean debit);

}

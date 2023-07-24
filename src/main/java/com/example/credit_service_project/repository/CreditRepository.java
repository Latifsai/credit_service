package com.example.credit_service_project.repository;

import com.example.credit_service_project.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CreditRepository extends JpaRepository<Credit, UUID> {
}

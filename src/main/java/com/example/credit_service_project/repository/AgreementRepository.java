package com.example.credit_service_project.repository;

import com.example.credit_service_project.entity.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AgreementRepository extends JpaRepository<Agreement, UUID> {
}

package com.example.credit_service_project.repository;

import com.example.credit_service_project.entity.CreditOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CreditOrderRepository extends JpaRepository<CreditOrder, UUID> {
}

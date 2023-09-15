package com.example.credit_service_project.repository;

import com.example.credit_service_project.entity.CreditOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CreditOrderRepository extends JpaRepository<CreditOrder, UUID> {
}

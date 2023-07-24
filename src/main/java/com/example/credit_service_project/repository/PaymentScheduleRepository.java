package com.example.credit_service_project.repository;

import com.example.credit_service_project.entity.PaymentSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentScheduleRepository extends JpaRepository<PaymentSchedule, UUID> {
}

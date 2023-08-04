package com.example.credit_service_project.repository;

import com.example.credit_service_project.entity.Client;
import com.example.credit_service_project.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ManagerRepository extends JpaRepository<Manager, UUID> {
}

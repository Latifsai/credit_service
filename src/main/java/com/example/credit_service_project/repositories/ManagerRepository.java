package com.example.credit_service_project.repositories;

import com.example.credit_service_project.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ManagerRepository extends JpaRepository<Manager, UUID> {
    Optional<Manager> findByName(String name);
}

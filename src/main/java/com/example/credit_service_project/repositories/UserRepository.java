package com.example.credit_service_project.repositories;

import com.example.credit_service_project.entity.Role;
import com.example.credit_service_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByName(String name);
}

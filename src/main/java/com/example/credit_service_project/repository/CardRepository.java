package com.example.credit_service_project.repository;

import com.example.credit_service_project.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID> {
}

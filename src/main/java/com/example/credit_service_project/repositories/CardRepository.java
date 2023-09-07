package com.example.credit_service_project.repositories;

import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID> {
    List<Card> findByAccount(Account account);

}

package com.example.credit_service_project.repository;

import com.example.credit_service_project.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID> {
    Optional<Card> findByCardNumberAndHolderName(String number, String name);
    Optional<Card> findByIdAndCardNumber(UUID id, String number);
    List<Card> findAllByAccount_AccountNumber(String accountNumber);
}

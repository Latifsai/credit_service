package com.example.credit_service_project.DTO.cardDTO;

import com.example.credit_service_project.entity.enums.PaymentSystem;
import lombok.Value;

import java.util.UUID;

@Value
public class CreateCardRequestDTO {
    UUID accountID;
    String accountNumber;
    Integer yearAccessibility;
    boolean isDigitalValet;
    PaymentSystem paymentSystem;
}

package com.example.credit_service_project.dto.cardDTO;

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

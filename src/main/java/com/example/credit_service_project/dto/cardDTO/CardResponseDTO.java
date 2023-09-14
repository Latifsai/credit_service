package com.example.credit_service_project.dto.cardDTO;

import com.example.credit_service_project.entity.enums.CardStatus;
import com.example.credit_service_project.entity.enums.PaymentSystem;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
public class CardResponseDTO {
    UUID id;
    String cardNumber;
    String holderName;
    String IBAN;
    BigDecimal balance;
    PaymentSystem paymentSystem;
    CardStatus cardStatus;
    String accountNumber;
    LocalDate expirationDate;
    String currency;
}

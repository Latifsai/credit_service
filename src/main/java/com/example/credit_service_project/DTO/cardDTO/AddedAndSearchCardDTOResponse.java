package com.example.credit_service_project.DTO.cardDTO;

import com.example.credit_service_project.entity.enums.CardStatus;
import com.example.credit_service_project.entity.enums.PaymentSystem;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Value
public class AddedAndSearchCardDTOResponse {
    UUID id;
    String cardNumber;
    String holderName;
    BigDecimal balance;
    PaymentSystem paymentSystem;
    CardStatus cardStatus;
    String accountNumber;
    LocalDate expirationDate;
    String currency;
}

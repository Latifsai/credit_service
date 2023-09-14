package com.example.credit_service_project.services.generators;


import com.example.credit_service_project.dto.cardDTO.CardResponseDTO;
import com.example.credit_service_project.entity.enums.CardStatus;
import com.example.credit_service_project.entity.enums.PaymentSystem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static java.time.Month.APRIL;

public class DTOCardCreator {


    public static CardResponseDTO getCardResponse() {
        return CardResponseDTO.builder()
                .id(UUID.fromString("38859752-e264-43e0-ae34-02acef9e6061"))
                .cardNumber("A10B3U3OI9")
                .holderName("John Snow")
                .IBAN("0000 0000 0000 0000 00")
                .balance(BigDecimal.valueOf(3000))
                .paymentSystem(PaymentSystem.VISA)
                .cardStatus(CardStatus.ACTIVE)
                .accountNumber("A10B3U3OI9")
                .expirationDate(LocalDate.of(2023, APRIL, 21))
                .currency("USD")
                .build();
    }

    public static CardResponseDTO getUpdatedCardResponse() {
        return CardResponseDTO.builder()
                .id(UUID.fromString("38859752-e264-43e0-ae34-02acef9e6061"))
                .cardNumber("A10B3U3OI9")
                .holderName("John Snow")
                .IBAN("0000 0000 0000 0000 00")
                .balance(BigDecimal.valueOf(4000))
                .paymentSystem(PaymentSystem.VISA)
                .cardStatus(CardStatus.ACTIVE)
                .accountNumber("A10B3U3OI9")
                .expirationDate(LocalDate.of(2023, APRIL, 21))
                .currency("USD")
                .build();
    }


}

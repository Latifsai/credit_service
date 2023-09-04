package com.example.credit_service_project.services.generators;


import com.example.credit_service_project.DTO.cardDTO.AddCardDTORequest;
import com.example.credit_service_project.DTO.cardDTO.CardDTOResponse;
import com.example.credit_service_project.entity.enums.CardStatus;
import com.example.credit_service_project.entity.enums.PaymentSystem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static java.time.Month.APRIL;

public class DTOCardCreator {
    public static AddCardDTORequest getAddAccountDTORequest() {
        return new AddCardDTORequest(
                UUID.fromString("00009999-2222-1111-a456-426655440000"),
                "A10B3U3OI9",
                "Wertach Strasse 34",
                3,
                true,
                PaymentSystem.VISA
        );
    }

    public static AddCardDTORequest getAddAccountDTORequestWithValidationErrors() {
        return new AddCardDTORequest(
                UUID.fromString("00009999-2222-1111-a456-426655440000"),
                "",
                null,
                14,
                true,
                PaymentSystem.VISA
        );
    }

    public static CardDTOResponse getCardResponse() {
        return new CardDTOResponse(
                UUID.fromString("00009999-2222-1111-a456-426655440000"),
                "A10B3U3OI9",
                "Oleg Kirilov",
                BigDecimal.valueOf(3000),
                PaymentSystem.VISA,
                CardStatus.ACTIVE,
                "A10B3U3OI9",
                LocalDate.of(2023, APRIL, 21),
                "$"
        );
    }

    public static CardDTOResponse getUpdatedCardResponse() {
        return new CardDTOResponse(
                UUID.fromString("00009999-2222-1111-a456-426655440000"),
                "A10B3U3OI9",
                "Oleg Kirilov",
                BigDecimal.valueOf(5000),
                PaymentSystem.VISA,
                CardStatus.ACTIVE,
                "A10B3U3OI9",
                LocalDate.of(2023, APRIL, 21),
                "$"
        );
    }

    public static List<CardDTOResponse> getListResponse() {
      return List.of(getCardResponse());
    }
}

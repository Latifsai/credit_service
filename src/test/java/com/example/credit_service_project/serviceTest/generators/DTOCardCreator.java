package com.example.credit_service_project.serviceTest.generators;


import com.example.credit_service_project.DTO.cardDTO.AddCardDTORequest;
import com.example.credit_service_project.DTO.cardDTO.CardDTOResponse;
import com.example.credit_service_project.entity.enums.CardStatus;
import com.example.credit_service_project.entity.enums.PaymentSystem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.time.Month.APRIL;

public class DTOCardCreator {
    public static AddCardDTORequest getAddAccountDTORequest() {
        return new AddCardDTORequest(
                UUID.fromString("00009999-2222-1111-a456-426655440000"),
                "A10B3U3OI9",
                "Oleg Kirilov",
                LocalDate.of(2020, APRIL, 21),
                "Wertach Strasse 34",
                3,
                true,
                PaymentSystem.VISA
        );
    }

    public static CardDTOResponse getAddResponse() {
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

    public static UpdateCardDTOResponse getUpdateResponse() {
        return new UpdateCardDTOResponse(
                UUID.fromString("00009999-2222-1111-a456-426655440000"),
                "A10B3U3OI9",
                "Oleg Kirilov",
                new BigDecimal("5000"),
                "Wertach Strasse 34",
                CardStatus.ACTIVE
        );
    }

    public static List<GetCardsResponse> getResponse() {
        List<GetCardsResponse> list = new ArrayList<>();
        list.add(new GetCardsResponse(
                UUID.fromString("00009999-2222-1111-a456-426655440000"),
                "A10B3U3OI9",
                "Oleg Kirilov",
                LocalDate.of(2023, APRIL, 21),
                new BigDecimal("5000"),
                "Wertach Strasse 34",
                true,
                PaymentSystem.VISA,
                CardStatus.ACTIVE
        ));
        return list;
    }
}

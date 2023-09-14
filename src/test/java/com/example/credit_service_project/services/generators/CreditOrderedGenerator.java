package com.example.credit_service_project.services.generators;

import com.example.credit_service_project.dto.creditOrderDTO.CreateCreditOrderResponseDTO;
import com.example.credit_service_project.dto.creditOrderDTO.CreditOrderResponseDTO;
import com.example.credit_service_project.entity.enums.CreditOrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

public class CreditOrderedGenerator {
    public static CreditOrderResponseDTO getResponse() {
        return new CreditOrderResponseDTO(
                UUID.fromString("c3009377-3b57-4965-8540-69d56fce34f4"),
                "1054ADNOFD",
                BigDecimal.valueOf(10000),
                LocalDate.of(2023, Month.SEPTEMBER, 4),
                120,
                12,
                CreditOrderStatus.IN_REVIEW
        );
    }

    public static CreateCreditOrderResponseDTO getCreateResponse() {
        return CreateCreditOrderResponseDTO.builder()
                .productID(1L)
                .productName("BMW")
                .id(UUID.fromString("c3009377-3b57-4965-8540-69d56fce34f4"))
                .number("1054ADNOFD")
                .amount(BigDecimal.valueOf(10000))
                .creationDate(LocalDate.of(2023, Month.SEPTEMBER, 4))
                .maxPeriodMonths(120)
                .minPeriodMonths(12)
                .creditOrderStatus(CreditOrderStatus.IN_REVIEW)
                .currency("USD")
                .build();
    }


}

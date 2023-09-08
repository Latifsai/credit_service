package com.example.credit_service_project.services.generators;

import com.example.credit_service_project.DTO.paymentDTO.PaymentResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static java.time.Month.SEPTEMBER;

public class DTOPaymentCreator {

    public static PaymentResponseDTO getPaymentResponseDTO() {
        return new PaymentResponseDTO(
                UUID.fromString("79a8b4d5-8e2c-4107-b171-f64b04e086dc"),
                LocalDate.of(2023, SEPTEMBER, 18),
                null,
                BigDecimal.ZERO,
                new BigDecimal("300.54"),
                false
        );
    }
}

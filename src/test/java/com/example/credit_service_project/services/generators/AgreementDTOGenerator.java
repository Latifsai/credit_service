package com.example.credit_service_project.services.generators;

import com.example.credit_service_project.DTO.agreementDTO.AgreementResponse;

import java.time.LocalDate;
import java.util.UUID;

import static java.time.Month.SEPTEMBER;

public class AgreementDTOGenerator {
    public static AgreementResponse getResponse() {
        return new AgreementResponse(
                UUID.fromString("34d824ef-da02-4845-af3d-2aba7f6336ca"),
                "AAAAA1010",
                LocalDate.of(2023, SEPTEMBER, 1),
                LocalDate.of(2024, SEPTEMBER, 1),
                true
        );
    }
}

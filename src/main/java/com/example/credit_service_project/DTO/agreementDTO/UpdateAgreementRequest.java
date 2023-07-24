package com.example.credit_service_project.DTO.agreementDTO;

import lombok.Value;

import java.time.LocalDate;

@Value
public class UpdateAgreementRequest {
    String number;
    boolean active;

    LocalDate terminationDate;
}

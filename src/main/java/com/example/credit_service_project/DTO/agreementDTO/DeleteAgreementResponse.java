package com.example.credit_service_project.DTO.agreementDTO;

import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class DeleteAgreementResponse {
    UUID id;
    String number;
    boolean active;
    LocalDate terminationDate;
}

package com.example.credit_service_project.dto.agreementDTO;

import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class AgreementResponse {
    UUID id;
    String number;
    LocalDate agreementDate;
    LocalDate terminationDate;
    boolean active;
}

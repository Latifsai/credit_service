package com.example.credit_service_project.DTO.agreementDTO;

import lombok.Value;

@Value
public class SearchAndDeleteAgreementRequest {
    String number;
    boolean active;
}

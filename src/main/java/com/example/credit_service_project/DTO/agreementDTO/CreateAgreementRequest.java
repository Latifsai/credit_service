package com.example.credit_service_project.DTO.agreementDTO;

import lombok.Value;
import java.util.UUID;

@Value
public class CreateAgreementRequest {
    UUID creditOrderID;
    Integer numberLength;
}

package com.example.credit_service_project.dto.agreementDTO;

import lombok.Value;
import java.util.UUID;

@Value
public class CreateAgreementRequest {
    UUID creditOrderID;
    Integer numberLength;
}

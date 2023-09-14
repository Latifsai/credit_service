package com.example.credit_service_project.dto.operationDTO;

import lombok.Value;

import java.util.UUID;

@Value
public class GetBelongsAccountOperationsRequest {
    UUID accountID;
    String accountNumber;
}

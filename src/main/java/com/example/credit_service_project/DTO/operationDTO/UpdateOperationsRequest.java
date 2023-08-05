package com.example.credit_service_project.DTO.operationDTO;

import com.example.credit_service_project.entity.enums.OperationType;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.util.UUID;

@Value
public class UpdateOperationsRequest {
    @NotNull(message = "ID must not be null!")
    UUID id;
    boolean isDebit;

    OperationType type;
    String operationDetails;
}

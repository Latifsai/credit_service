package com.example.credit_service_project.DTO.operationDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.util.UUID;

@Value
public class SearchOperationRequest {
    @NotNull(message = "ID must not be not null!")
    UUID id;
    boolean isDebit;
}

package com.example.credit_service_project.DTO.operationDTO;

import com.example.credit_service_project.entity.enums.OperationType;
import lombok.Value;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@Value
public class UpdateOperationsRequest {
    UUID id;
    boolean isDebit;

    OperationType type;
    String operationDetails;
}

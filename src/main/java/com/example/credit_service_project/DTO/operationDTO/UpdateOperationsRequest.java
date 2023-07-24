package com.example.credit_service_project.DTO.operationDTO;

import com.example.credit_service_project.entity.enums.OperationType;
import lombok.Value;

import java.sql.Timestamp;
import java.util.UUID;

@Value
public class UpdateOperationsRequest {
    UUID id;
    Timestamp operationEndMark;
    boolean isDebit;

    OperationType type;
    StringBuilder operationDetails;
}

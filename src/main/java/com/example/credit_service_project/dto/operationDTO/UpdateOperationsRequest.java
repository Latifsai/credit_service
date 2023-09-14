package com.example.credit_service_project.dto.operationDTO;

import com.example.credit_service_project.entity.enums.OperationType;
import lombok.Value;

import java.util.UUID;

@Value
public class UpdateOperationsRequest {
    UUID id;
    OperationType type;
    String operationDetails;
}

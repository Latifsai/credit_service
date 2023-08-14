package com.example.credit_service_project.DTO.operationDTO;

import com.example.credit_service_project.entity.enums.OperationType;
import lombok.Value;

import java.util.UUID;

@Value
public class AddOperationPaymentRequest {
    String operationDetails;
    OperationType type;
}

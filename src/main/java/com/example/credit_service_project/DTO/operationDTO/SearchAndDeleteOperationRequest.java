package com.example.credit_service_project.DTO.operationDTO;

import lombok.Value;

import java.sql.Timestamp;
import java.util.UUID;

@Value
public class SearchAndDeleteOperationRequest {
    UUID id;
    Timestamp operationEndMark;
    boolean isDebit;
}

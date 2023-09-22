package com.example.credit_service_project.dto.creditHistoryDTO;

import com.example.credit_service_project.entity.enums.CreditHistoryStatus;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class CreditHistoryResponse {
    UUID id;
    CreditHistoryStatus status;
    Integer delayAmount;
    String accountNumber;
}

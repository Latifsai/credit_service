package com.example.credit_service_project.dto.creditOrderDTO;

import com.example.credit_service_project.entity.enums.CreditOrderStatus;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class CheckCreditOrderStatusResponse {
    UUID id;
    CreditOrderStatus status;
    LocalDate createDate;
    Integer amountDaysInProcessing;
}

package com.example.credit_service_project.DTO.creditDTO;

import com.example.credit_service_project.entity.enums.CreditStatus;
import lombok.Value;

import java.util.UUID;

@Value
public class DeleteCreditDtoResponse {
    UUID id;
    Integer periodMonth;
    CreditStatus creditStatus;
}

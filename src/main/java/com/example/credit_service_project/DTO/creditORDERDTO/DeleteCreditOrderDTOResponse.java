package com.example.credit_service_project.DTO.creditORDERDTO;

import com.example.credit_service_project.entity.enums.CreditOrderStatus;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class DeleteCreditOrderDTOResponse {
    UUID id;
    String number;
    CreditOrderStatus creditOrderStatus;
    LocalDate creationDate;
}

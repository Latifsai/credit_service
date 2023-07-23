package com.example.credit_service_project.DTO.paymentDTO;

import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class SearchAndDeletePaymentScheduleDTORequest {
    UUID uuid;
    LocalDate paymentDate;
}

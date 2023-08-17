package com.example.credit_service_project.DTO.client;

import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class UpdateClientRequest {
    UUID id;
    BigDecimal salary;
    BigDecimal passiveIncome;
    BigDecimal expenses;
    String address;
    String email;
    String phone;
}

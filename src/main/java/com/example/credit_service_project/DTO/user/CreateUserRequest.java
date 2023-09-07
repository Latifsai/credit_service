package com.example.credit_service_project.DTO.user;

import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class CreateUserRequest {
    String name;
    String surname;
    BigDecimal salary;
    BigDecimal passiveIncome;
    BigDecimal expenses;
    String address;
    String email;
    String phone;
    String role;
    String password;
}

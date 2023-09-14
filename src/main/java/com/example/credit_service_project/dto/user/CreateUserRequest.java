package com.example.credit_service_project.dto.user;

import lombok.Value;

import java.math.BigDecimal;

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

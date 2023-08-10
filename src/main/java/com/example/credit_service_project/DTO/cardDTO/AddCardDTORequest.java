package com.example.credit_service_project.DTO.cardDTO;

import com.example.credit_service_project.entity.enums.PaymentSystem;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.util.UUID;

@Value
public class AddCardDTORequest {
    UUID accountID;
    String accountNumber;
    String deliveryAddress;
    Integer yearAccessibility;
    boolean isDigitalValet;
    PaymentSystem paymentSystem;
}

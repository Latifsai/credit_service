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

    @NotNull(message = "accountId must not be null!")
    UUID accountId;

    @NotNull(message = "accountNumber must not be null!")
    @NotEmpty(message = "accountNumber must not be empty!")
    String accountNumber;

    @NotNull(message = "deliveryAddress must not be null!")
    @NotEmpty(message = "deliveryAddress must not be empty!")
    String deliveryAddress;

    @Max(value = 8, message = "Max year accessibility is 8")
    @Min(value = 3, message = "Min year accessibility is 8")
    Integer yearAccessibility;

    boolean isDigitalValet;

    PaymentSystem paymentSystem;
}

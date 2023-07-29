package com.example.credit_service_project.DTO.cardDTO;

import com.example.credit_service_project.entity.enums.PaymentSystem;
import lombok.Value;
import java.time.LocalDate;
import java.util.UUID;

@Value
public class AddCardDTORequest {
     UUID accountId;
     String accountNumber;

     String holderName;
     LocalDate openingDate;
     String deliveryAddress;
     LocalDate accountOpeningTime;
     Integer yearAccessibility;
     boolean isDigitalValet;
     PaymentSystem paymentSystem;
}

package com.example.credit_service_project.DTO.cardDTO;

import com.example.credit_service_project.entity.enums.PaymentSystem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCardDTORequest {
     private UUID accountId;
     private String accountNumber;

     private String holderName;
     private LocalDate openingDate;
     private String deliveryAddress;
     private Integer yearAccessibility;
     private boolean isDigitalValet;
     private PaymentSystem paymentSystem;
}

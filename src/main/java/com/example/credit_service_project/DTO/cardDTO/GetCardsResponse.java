package com.example.credit_service_project.DTO.cardDTO;


import com.example.credit_service_project.entity.enums.CardStatus;
import com.example.credit_service_project.entity.enums.PaymentSystem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCardsResponse {
    private UUID id;
    private String cardNumber;
    private String holderName;
    private LocalDate expirationDate;
    private BigDecimal balance;
    private String deliveryAddress;
    private boolean isDigitalValet;
    private PaymentSystem paymentSystem;
    private CardStatus cardStatus;
}

package com.example.credit_service_project.DTO.cardDTO;

import com.example.credit_service_project.entity.enums.CardStatus;
import com.example.credit_service_project.entity.enums.PaymentSystem;

import java.math.BigDecimal;
import java.util.UUID;

public class AddedAndSearchCardDTOResponse {
    private UUID id;
    private String cardNumber;
    private String holderName;
    private BigDecimal balance;
    private PaymentSystem paymentSystem;
    private CardStatus cardStatus;
    private String accountNumber;
    private BigDecimal unpaidLoanDebt;
    private BigDecimal unpaidPercentageLoanDebt;
    private String currency;
}

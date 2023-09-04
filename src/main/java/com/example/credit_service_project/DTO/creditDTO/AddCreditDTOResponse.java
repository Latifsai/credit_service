package com.example.credit_service_project.DTO.creditDTO;

import com.example.credit_service_project.DTO.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.entity.enums.CalculationType;
import com.example.credit_service_project.entity.enums.CreditStatus;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Value
@Builder
public class AddCreditDTOResponse {
    UUID id;
    String creditType;
    BigDecimal creditSum;
    BigDecimal interestRate;
    Integer periodMonth;
    BigDecimal fine;
    boolean needDeposit;
    CreditStatus creditStatus;
    String currency;

    String accountNumber;
    //agreement
    String agreementNumber;
    LocalDate terminationDate;
    //creditOrder
    String creditOrderNumber;
    //product
    Long productID;
    String productName;
    CalculationType calculationType;
    List<PaymentResponseDTO> list;
}

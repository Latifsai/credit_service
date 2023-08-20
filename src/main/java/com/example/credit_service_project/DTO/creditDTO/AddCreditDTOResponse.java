package com.example.credit_service_project.DTO.creditDTO;

import com.example.credit_service_project.DTO.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.entity.enums.CalculationType;
import com.example.credit_service_project.entity.enums.CreditStatus;
import lombok.Value;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Value
public class AddCreditDTOResponse {
    UUID id;
    String creditType;
    BigDecimal creditSum;
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
    BigInteger productID;
    String name;
    CalculationType calculationType;
    List<PaymentResponseDTO> list;
}

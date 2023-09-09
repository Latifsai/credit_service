package com.example.credit_service_project.services.generators;

import com.example.credit_service_project.DTO.creditDTO.CreateCreditDTOResponse;
import com.example.credit_service_project.DTO.creditDTO.CreditResponseDTO;
import com.example.credit_service_project.entity.enums.CalculationType;
import com.example.credit_service_project.entity.enums.CreditStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

public class CreditDTOGenerator {
    public static CreateCreditDTOResponse getCreationResponse() {
        return CreateCreditDTOResponse.builder()
                .id(UUID.fromString("cdaf24f4-cb58-4027-9f2d-6c4cb3124af2"))
                .creditType("consumer credit")
                .interestRate(BigDecimal.TEN)
                .periodMonth(12)
                .fine(BigDecimal.ZERO)
                .needDeposit(false)
                .creditStatus(CreditStatus.ACTIVE)
                .currency("USD")
                .accountNumber("ASGRG#$")
                .agreementNumber("HGRGI293")
                .terminationDate(LocalDate.of(2024, Month.SEPTEMBER, 9))
                .creditOrderNumber("<PJONJIO123")
                .productID(1L)
                .productName("BMW X5")
                .calculationType(CalculationType.ANNUITY)
                .build();
    }

    public static CreditResponseDTO getResponse() {
        return CreditResponseDTO.builder()
                .id(UUID.fromString("cdaf24f4-cb58-4027-9f2d-6c4cb3124af2"))
                .creditType("consumer credit")
                .interestRate(BigDecimal.TEN)
                .periodMonth(12)
                .fine(BigDecimal.ZERO)
                .needDeposit(false)
                .creditStatus(CreditStatus.ACTIVE)
                .currency("USD")
                .build();
    }

}

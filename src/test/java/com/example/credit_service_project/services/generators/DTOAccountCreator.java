package com.example.credit_service_project.services.generators;

import com.example.credit_service_project.dto.accountDTO.AccountResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static com.example.credit_service_project.entity.enums.AccountStatus.ACTIVE;
import static java.math.BigDecimal.ZERO;
import static java.time.Month.APRIL;

public class DTOAccountCreator {

    public static AccountResponseDTO createDTOResponse() {
        return AccountResponseDTO.builder()
                .id(UUID.fromString("22eb47fe-79be-4130-9727-a6c71e2664b6"))
                .accountNumber("A10B3U3OI9")
                .clientInitial("John Snow")
                .loanDebt(ZERO)
                .percentageDebt(ZERO)
                .status(ACTIVE)
                .balance(new BigDecimal("3000"))
                .closingDate(LocalDate.of(2027, APRIL, 30))
                .unpaidCreditSum(ZERO)
                .currency("USD")
                .country("United States")
                .build();
    }

    public static AccountResponseDTO getUpdatedDTOResponse() {
        return AccountResponseDTO.builder()
                .id(UUID.fromString("22eb47fe-79be-4130-9727-a6c71e2664b6"))
                .accountNumber("A10B3U3OI9")
                .clientInitial("John Snow")
                .loanDebt(ZERO)
                .percentageDebt(ZERO)
                .status(ACTIVE)
                .balance(new BigDecimal("4000"))
                .closingDate(LocalDate.of(2027, APRIL, 30))
                .unpaidCreditSum(ZERO)
                .currency("USD")
                .country("United States")
                .build();
    }

}

package com.example.credit_service_project.DTO.accountDTO;

import com.example.credit_service_project.entity.enums.AccountStatus;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAccountDTORequest {
    private UUID clientId;
    private BigDecimal loanDebt;
    private BigDecimal percentageDebt;
    private String county;
    private BigDecimal balance;
    private String currency;
    private AccountStatus status;
    private Integer accountNumberLength;
    private Integer yearsAmountForClosingDate;
}

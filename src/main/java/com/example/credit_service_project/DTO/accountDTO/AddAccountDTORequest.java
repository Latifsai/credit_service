package com.example.credit_service_project.DTO.accountDTO;

import com.example.credit_service_project.entity.enums.AccountStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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
    @PositiveOrZero
    private BigDecimal loanDebt;
    @PositiveOrZero
    private BigDecimal percentageDebt;
    @PositiveOrZero
    private BigDecimal balance;
    @NotNull
    @NotEmpty
    private String currency;
    @NotEmpty
    private AccountStatus status;

    private Integer accountNumberLength;
    private Long yearsAmountForClosingDate;
}

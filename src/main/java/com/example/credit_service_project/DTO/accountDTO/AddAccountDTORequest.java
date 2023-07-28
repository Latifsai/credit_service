package com.example.credit_service_project.DTO.accountDTO;

import com.example.credit_service_project.entity.enums.AccountStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAccountDTORequest {
    @PositiveOrZero
    BigDecimal loanDebt;
    @PositiveOrZero
    BigDecimal percentageDebt;
    @PositiveOrZero
    BigDecimal balance;
    @NotNull
    @NotEmpty
    String currency;
    @NotEmpty
    AccountStatus status;

    Integer accountNumberLength;
    Long yearsAmountForClosingDate;
}

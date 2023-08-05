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

    @NotNull(message = "ClientID must not not be null!")
    private UUID clientId;

    @PositiveOrZero(message = "LoanDebt must be 0 or more")
    private BigDecimal loanDebt;

    @PositiveOrZero(message = "percentageDebt must be 0 or more")
    private BigDecimal percentageDebt;

    @PositiveOrZero(message = "balance must be 0 or more")
    private BigDecimal balance;

    @Pattern(regexp = "[$€£¥₽₪₴]", message = "Format is not allowed in service!")
    private String currency;

    private AccountStatus status;

    @Max(value = 40, message = "maximum number length is 40")
    @Min(value = 7, message = "minimum number length is 7")
    private Integer accountNumberLength;

    @Max(value = 10, message = "maximum years amount is 41")
    @Min(value = 3, message = "minimum years amount is 3")
    private Integer yearsAmountForClosingDate;
}

package com.example.credit_service_project.DTO.operationDTO;

import com.example.credit_service_project.entity.enums.OperationType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Value
public class AddOperationRequestSpendingOrReplenishment {
     @NotNull(message = "accountID must be not null!")
     UUID accountID;
     UUID cardID;
     String accountNumber;
     String cardNumber;

     @Positive // make the annotation, which will validate BigDecimal
     BigDecimal sum;

     @NotNull(message = "OperationType must be not null!")
     OperationType type;

     @NotNull(message = "operationDetails must be not null!")
     @NotEmpty(message = "operationDetails must be not empty!")
     String operationDetails;
}

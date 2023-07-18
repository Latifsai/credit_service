package com.example.credit_service_project.entity;

import com.example.credit_service_project.entity.enums.AccountStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class Account {
    @Id
    private UUID id;
    private String accountNumber;
    private BigDecimal loanDebt;
    private BigDecimal percentageDebt;
    private AccountStatus status;
    private LocalDateTime openingDate;
    private LocalDateTime closingDate;
    private String currency;
}

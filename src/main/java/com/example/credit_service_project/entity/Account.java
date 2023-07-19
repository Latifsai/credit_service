package com.example.credit_service_project.entity;

import com.example.credit_service_project.entity.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Data
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "loan_debt")
    private BigDecimal loanDebt;
    @Column(name = "percentage_debt")
    private BigDecimal percentageDebt;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @Column(name = "opening_date")
    private LocalDateTime openingDate;
    @Column(name = "closing_date")
    private LocalDateTime closingDate;
    @Column(name = "unpaid_load_debt")
    private BigDecimal unpaidLoanDebt;
    @Column(name = "unpaid_percentage_load_debt")
    private BigDecimal unpaidPercentageLoanDebt;
    @Column(name = "currency")
    private String currency;

    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY,
            orphanRemoval = true, cascade = {MERGE, PERSIST, REFRESH})
    private Credit credit;
}

package com.example.credit_service_project.entity;

import com.example.credit_service_project.entity.enums.CreditStatus;
import com.example.credit_service_project.entity.enums.CreditType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Entity
@Data
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "credit_type")
    @Enumerated(EnumType.STRING)
    private CreditType creditType;
    @Column(name = "credit_Limit")
    private BigDecimal creditLimit;
    @Column(name = "interest_rate")
    private BigDecimal interestRate; // процентная сумма
    @Column(name = "surcharge")
    private BigDecimal surcharge; // пеня(в процентах)
    @Column(name = "need_deposit")
    private boolean needDeposit;
    @Column(name = "credit_status")
    @Enumerated(EnumType.STRING)
    private CreditStatus creditStatus;
    @Column(name = "currency")
    private String currency;

    @OneToOne(mappedBy = "credit", fetch = FetchType.LAZY,
    orphanRemoval = true, cascade = {MERGE, PERSIST, REFRESH})
    private Account account;
}

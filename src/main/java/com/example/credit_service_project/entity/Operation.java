package com.example.credit_service_project.entity;

import com.example.credit_service_project.entity.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Data
@Table(name = "operations")
@AllArgsConstructor
@NoArgsConstructor

public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "sum")
    private BigDecimal sum;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private OperationType type;

    @Column(name = "operation_end_mark")
    private LocalDateTime operationEndMark;

    @Column(name = "operation_details")
    private String operationDetails;

    @Column(name = "debit")
    private boolean debit;

    @Column(name = "currency")
    private String currency;

    @ManyToOne(fetch = FetchType.LAZY, cascade = ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;
    //в таблице будет создано поле account_id на основе id в классе Account
}

package com.example.credit_service_project.entity;

import com.example.credit_service_project.entity.enums.OperationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
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
    @Positive(message = "Sum must not be negative!")
    private BigDecimal sum;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Type must not be null!")
    private OperationType type;

    @Column(name = "operation_end_mark")
    @NotNull(message = "OperationEndMark must not be null!")
    private LocalDateTime operationEndMark;

    @Column(name = "operation_details")
    @NotNull(message = "OperationDetails must not be null!")
    @NotEmpty(message = "OperationDetails must not be empty!")
    private String operationDetails;

    @Column(name = "debit")
    private boolean debit;

    @Column(name = "currency")
    @Pattern(regexp = "^[A-Z]{3}$",message = "Format is not allowed in service!")
    private String currency;

    @ManyToOne(fetch = FetchType.LAZY, cascade = ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;
    //в таблице будет создано поле account_id на основе id в классе Account
}

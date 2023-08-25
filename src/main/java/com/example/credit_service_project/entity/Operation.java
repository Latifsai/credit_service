package com.example.credit_service_project.entity;

import com.example.credit_service_project.entity.enums.OperationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Type must not be blank!")
    private OperationType type;

    @Column(name = "operation_end_mark")
    @NotBlank(message = "Operation end mark must not be blank!")
    private LocalDateTime operationEndMark;

    @Column(name = "operation_details")
    @NotBlank(message = "Operation details must not be blank!")
    private String operationDetails;

    @Column(name = "debit")
    private boolean debit;

    @Column(name = "currency")
    @Pattern(regexp = "^[A-Z]{3}$",message = "Format is not allowed in service!")
    @NotBlank(message = "Currency must not be blank!")
    private String currency;

    @ManyToOne(fetch = FetchType.LAZY, cascade = ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;
    //в таблице будет создано поле account_id на основе id в классе Account
}

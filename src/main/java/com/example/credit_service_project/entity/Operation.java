package com.example.credit_service_project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@Table(name = "operations")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "sum")
    private BigDecimal sum;

    @Column(name = "operation_end_mark")
    private Timestamp operationEndMark;

    @Column(name = "operation_details")
    private StringBuilder operationDetails;

    @Column(name = "currency")
    private String currency;

    @OneToOne(fetch = FetchType.LAZY, cascade = {MERGE, PERSIST, REFRESH})
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;
    //в таблице будет создано поле account_id на основе id в классе Account

    @ManyToOne(fetch = FetchType.LAZY, cascade = {MERGE, PERSIST, REFRESH})
    @JoinColumn(name = "operationType_id", referencedColumnName = "id")
    private OperationType operationType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Objects.equals(account, operation.account) && Objects.equals(operationType, operation.operationType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account, operationType);
    }
}

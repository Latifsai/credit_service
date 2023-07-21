package com.example.credit_service_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "operation_types")
@ToString
public class OperationType {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "type")
    private String type;

    @Column(name = "is_debit")
    private boolean isDebit;
    //Дебетовая операция - это финансовая транзакция,
    // при которой средства списываются с банковского счета клиента или дебетовой карты для оплаты товаров,
    // услуг или других расходов. Когда происходит дебетовая операция,
    // сумма денег снимается с баланса счета клиента и перечисляется получателю платежа.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationType that = (OperationType) o;
        return Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}

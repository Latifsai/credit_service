package com.example.credit_service_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@Table(name = "payment_schedules")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "payment_date")
    @NotNull(message = "paymentDate must not be null!")
    private LocalDate paymentDate;

    @Column(name = "actual_payment_date")
    private LocalDate actualPaymentDate;

    @Column(name = "surcharge")
    @PositiveOrZero(message = "surcharge must not be negative!")
    private BigDecimal surcharge; // пеня(посчитаная)

    @Column(name = "monthly_payment")
    @Positive(message = "monthlyAmount must not be negative!")
    private BigDecimal monthlyPayment;

    @Column(name = "is_paid")
    private boolean isPaid;

    @ManyToOne(cascade = {MERGE, PERSIST, REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentSchedule that = (PaymentSchedule) o;
        return Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account);
    }
}

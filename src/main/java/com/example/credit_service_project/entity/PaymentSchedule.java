package com.example.credit_service_project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.REFRESH;
import static jakarta.persistence.CascadeType.PERSIST;

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
    private LocalDate paymentDate;

    @Column(name = "actual_payment_date")
    private LocalDate actualPaymentDate;

    @Column(name = "surcharge")
    private BigDecimal surcharge; // пеня(посчитаная)

    @Column(name = "main_payment")
    private BigDecimal mainPayment;

    @Column(name = "rate_payment")
    private BigDecimal ratePayment;

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
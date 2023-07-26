package com.example.credit_service_project.entity;

import com.example.credit_service_project.entity.enums.CreditOrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@Table(name = "credit_order")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreditOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "number")
    private String number;

    @Column(name = "amount")
    private BigDecimal amount; // amount of credit m

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "client_income")
    private BigDecimal clientIncome;

    @Column(name = "period_months")
    private Integer periodMonths;

    @Column(name = "client_monthly_expenditure")
    private BigDecimal clientMonthlyExpenditure;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CreditOrderStatus creditOrderStatus;

    //future: id of work-giver
    @OneToOne(mappedBy = "creditOrder", fetch = FetchType.LAZY,
    orphanRemoval = true, cascade = {MERGE, PERSIST, REFRESH})
    private Credit credit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditOrder that = (CreditOrder) o;
        return Objects.equals(number, that.number) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, product);
    }
}

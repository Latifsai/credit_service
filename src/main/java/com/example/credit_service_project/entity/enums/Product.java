package com.example.credit_service_project.entity.enums;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private BigInteger id;

    @Column(name = "name")
    private String name;

    @Column(name = "min_sum")
    private BigDecimal minSum;

    @Column(name = "max_sum")
    private BigDecimal maxSum;

    @Column(name = "min_interest_rate")
    private BigDecimal minInterestRate;

    @Column(name = "max_interest_rate")
    private BigDecimal maxInterestRate;

    @Column(name = "need_guaranty")
    private boolean needGuaranty;

    @Column(name = "early_guaranty")
    private boolean earlyRepayment;

    @Column(name = "need_income_details")
    private boolean needIncomeDetails;

    @Column(name = "min_period_month")
    private Integer minPeriodMonth;

    @Column(name = "max_period_month")
    private Integer maxPeriodMonth;

    @Column(name = "details")
    private String details;

    @Column(name = "grace_period_months")
    private Integer gracePeriodMonths;

    @Column(name = "rate_base")
    private String rateBase;

    @Column(name = "calculation_type")
    @Enumerated(EnumType.STRING)
    private CalculationType calculationType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

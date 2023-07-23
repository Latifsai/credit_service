package com.example.credit_service_project.entity;

import com.example.credit_service_project.entity.enums.CalculationType;
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

    @Column(name = "sum")
    private BigDecimal sum;

    @Column(name = "need_guaranty")
    private boolean needGuaranty;

    @Column(name = "early_guaranty")
    private boolean earlyRepayment;

    @Column(name = "need_income_details")
    private boolean needIncomeDetails;

    @Column(name = "details")
    private String details;

    @Column(name = "currency_code")
    private String currencyCode;

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

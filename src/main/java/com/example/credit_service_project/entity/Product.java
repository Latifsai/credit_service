package com.example.credit_service_project.entity;

import com.example.credit_service_project.entity.enums.CalculationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
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
    @NotBlank(message = "Name must not be blank!")
    private String name;

    @Column(name = "sum")
    @Positive(message = "Sum must not be negative!")
    private BigDecimal sum;

    @Column(name = "need_guaranty")
    private boolean needGuaranty;

    @Column(name = "early_guaranty")
    private boolean earlyRepayment;

    @Column(name = "need_income_details")
    private boolean needIncomeDetails;

    @Column(name = "details")
    @NotBlank(message = "Details must not be blank!")
    private String details;

    @Column(name = "currency_code")
    @NotBlank(message = "Currency code must not be blank!")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Format is not allowed in service!")
    private String currencyCode;

    @Column(name = "calculation_type")
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Calculation type code must not be blank!")
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

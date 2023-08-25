package com.example.credit_service_project.entity;

import com.example.credit_service_project.entity.enums.CreditOrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@Table(name = "credit_orders")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreditOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "number")
    @NotBlank(message = "Number must not be blank!")
    @Size(min = 6, max = 20, message = "Number must be between 6 and 20!")
    private String number;

    @Column(name = "amount")
    @Positive(message = "Amount must not be negative!")
    private BigDecimal amount; // amount of credit m

    @Column(name = "creation_date")
    @NotBlank(message = "Creation date must not be blank!")
    private LocalDate creationDate;

    @Column(name = "last_update_date")
    @NotBlank(message = "Last update date date must not be blank!")
    private LocalDate lastUpdateDate;

    @Column(name = "client_income")
    @NotBlank(message = "Client salary date must not be blank!")
    private BigDecimal clientSalary;

    @Column(name = "client_monthly_expenditure")
    @Positive(message = "clientMonthlyExpenditure must not be negative!")
    private BigDecimal clientMonthlyExpenditure;

    @Column(name = "passive_income")
    @PositiveOrZero(message = "periodMonths must not be negative!")
    private BigDecimal passiveIncome;

    @Column(name = "max_period_months")
    @Positive(message = "maxPeriodMonths must not be negative!")
    private Integer maxPeriodMonths;

    @Column(name = "min_period_months")
    @Positive(message = "minPeriodMonths must not be negative!")
    private Integer minPeriodMonths;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Credit order status must not be blank!")
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

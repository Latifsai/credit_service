package com.example.credit_service_project.entity;

import com.example.credit_service_project.entity.enums.CreditStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@Table(name = "credits")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "credit_type")
    @NotBlank(message = "Credit type must not be blank!")
    private String creditType; //

    @Column(name = "credit_Limit")
    @Positive(message = "Credit sum must not be negative!")
    private BigDecimal creditSum;

    @Column(name = "period_month")
    @Positive(message = "Period month must not be negative!")
    private Integer periodMonth;

    @Column(name = "interest_rate")
    @Positive(message = "Interest rate must not be negative!")
    private BigDecimal interestRate;

    @Column(name = "fine")
    @PositiveOrZero(message = "Fine must not be negative!")
    private BigDecimal fine;

    @Column(name = "need_deposit")
    private boolean needDeposit;

    @Column(name = "credit_status")
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "CreditStatus must not be blank!")
    private CreditStatus creditStatus;

    @Column(name = "currency")
    @Pattern(regexp = "^[A-Z]{3}$",message = "Format is not allowed in service!")
    @NotBlank(message = "Currency must not be blank!")
    private String currency;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, cascade = {MERGE, PERSIST, REFRESH})
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;
    // связь между таблицами будет установлена через поле credit в классе Account
    //<- обратная сторона

    @OneToOne(orphanRemoval = true, cascade = {MERGE, PERSIST, REFRESH})
    @JoinColumn(name = "agreement_id", referencedColumnName = "id")
    private Agreement agreement;

    @OneToOne(cascade = {MERGE, PERSIST, REFRESH}, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "creditOrder_id", referencedColumnName = "id")
    private CreditOrder creditOrder;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credit credit = (Credit) o;
        return Objects.equals(creditOrder, credit.creditOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creditOrder);
    }
}

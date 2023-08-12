package com.example.credit_service_project.entity;

import com.example.credit_service_project.entity.enums.CreditStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
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
    private String creditType; //

    @Column(name = "credit_Limit")
    private BigDecimal creditSum;

    @Column(name = "interest_rate")
    private BigDecimal interestRate; // процентная сумма, счтается

    @Column(name = "annual_Percentage")
    private BigDecimal annualPercentage; // проценты,основывается на rateBase

    @Column(name = "fine")
    private BigDecimal fine; // пеня в процентах, основывается на rateBase

    @Column(name = "need_deposit")
    private boolean needDeposit;

    @Column(name = "rate_base")
    private String rateBase;

    @Column(name = "period_month")
    private Integer periodMonth;

    @Column(name = "credit_status")
    @Enumerated(EnumType.STRING)
    private CreditStatus creditStatus;

    @Column(name = "currency")
    @Pattern(regexp = "^[A-Z]{3}$",message = "Format is not allowed in service!")
    private String currency;

    @OneToOne(mappedBy = "credit", fetch = FetchType.LAZY,
            orphanRemoval = true, cascade = {MERGE, PERSIST, REFRESH})
    private Account account;
    // связь между таблицами будет установлена через поле credit в классе Account
    //<- обратная сторона

    @OneToOne(mappedBy = "credit", orphanRemoval = true, cascade = {MERGE, PERSIST, REFRESH})
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

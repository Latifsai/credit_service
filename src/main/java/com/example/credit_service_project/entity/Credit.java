package com.example.credit_service_project.entity;

import com.example.credit_service_project.entity.enums.CreditStatus;
import jakarta.persistence.*;
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
    @Enumerated(EnumType.STRING)
    private String creditType; //

    @Column(name = "credit_Limit")
    private BigDecimal creditLimit;

    //сумма взятая в долг?
    @Column(name = "interest_rate")
    private BigDecimal interestRate; // процентная сумма

    @Column(name = "surcharge")
    private BigDecimal surcharge; // пеня(в процентах)

    @Column(name = "need_deposit")
    private boolean needDeposit;

    @Column(name = "credit_status")
    @Enumerated(EnumType.STRING)
    private CreditStatus creditStatus;

    @Column(name = "currency")
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

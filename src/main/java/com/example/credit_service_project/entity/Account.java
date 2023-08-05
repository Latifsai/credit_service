package com.example.credit_service_project.entity;

import com.example.credit_service_project.entity.enums.AccountStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@Table(name = "accounts")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @NotNull(message = "accountNumber must not be null!")
    @NotEmpty(message = "accountNumber must not be empty!")
    @Column(name = "account_number")
    private String accountNumber;

    @PositiveOrZero(message = "loanDebt must be more as 0!")
    @Column(name = "loan_debt")
    private BigDecimal loanDebt;

    @PositiveOrZero(message = "percentageDebt must be more as 0!")
    @Column(name = "percentage_debt")
    private BigDecimal percentageDebt;

    @NotNull(message = "status must not be null!")
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @NotNull(message = "balance must not be null!")
    @PositiveOrZero(message = "Balance must be greater than 0")
    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "opening_date")
    private LocalDate openingDate;

    @Column(name = "closing_date")
    private LocalDate closingDate;

    @PositiveOrZero(message = "unpaidPercentageLoanDebt must be greater than 0")
    @Column(name = "unpaid_load_debt")
    private BigDecimal unpaidLoanDebt;

    @PositiveOrZero(message = "unpaidPercentageLoanDebt must be greater than 0")
    @Column(name = "unpaid_percentage_load_debt")
    private BigDecimal unpaidPercentageLoanDebt;

    @NotNull(message = "Currency must not be null!")
    @Pattern(regexp = "[$€£¥₽₪₴]",message = "Format is not allowed in service!")
    @Column(name = "currency")
    private String currency;

    @OneToOne(cascade = {MERGE, PERSIST, REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_id", referencedColumnName = "id")
    private Credit credit;
    //будет создан раздел credit_id основанный на id из класса Credit,
    // по этому полу будет JOIN
    //-> владеющая сторона

    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY,
            orphanRemoval = true, cascade = {MERGE, PERSIST, REFRESH})
    private Operation operation;
    //указывает, что связь между таблицами будет установлена через поле account

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY,
            orphanRemoval = true, cascade = {MERGE, PERSIST, REFRESH})
    private List<PaymentSchedule> paymentSchedules;

    @OneToOne(mappedBy = "account", orphanRemoval = true,
            cascade = {MERGE, PERSIST, REFRESH}, fetch = FetchType.LAZY)
    @JsonIgnore // to ent a cirle
    private Card card;

    @OneToOne(cascade = {MERGE, PERSIST, REFRESH}, orphanRemoval = true)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountNumber, account.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }
}

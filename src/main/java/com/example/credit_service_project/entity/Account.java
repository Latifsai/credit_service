package com.example.credit_service_project.entity;

import com.example.credit_service_project.entity.enums.AccountStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@Table(name = "accounts")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @NotBlank(message = "accountNumber must not be blank!")
    @Size(min = 5, max = 16, message = "accountNumber must be between 5 and 16!")
    @Column(name = "account_number")
    private String accountNumber;

    @PositiveOrZero(message = "loanDebt must be more as 0!")
    @Column(name = "loan_debt")
    private BigDecimal loanDebt;

    @PositiveOrZero(message = "percentageDebt must be more as 0!")
    @Column(name = "percentage_debt")
    private BigDecimal percentageDebt;

    @Column(name = "country")
    @NotBlank(message = "country must not be blank!")
    private String country;

    @NotBlank(message = "status must not be blank!")
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @NotBlank(message = "balance must not be blank!")
    @PositiveOrZero(message = "Balance must be greater than 0")
    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "opening_date")
    @NotBlank(message = "openingDate must not be blank!")
    private LocalDate openingDate;

    @Column(name = "last_update_date")
    @NotBlank(message = "lastUpdateDate must not be blank!")
    private LocalDate lastUpdateDate;

    @Column(name = "closing_date")
    @NotBlank(message = "closingDate must not be blank!")
    private LocalDate closingDate;

    @PositiveOrZero(message = "unpaidPercentageLoanDebt must be greater than 0")
    @Column(name = "unpaid_credit_sum")
    private BigDecimal unpaidCreditSum;

    @NotBlank(message = "Currency must not be blank!")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Format is not allowed in service!")
    @Column(name = "currency")
    private String currency;

    @OneToOne(mappedBy = "account", cascade = {MERGE, PERSIST, REFRESH}, fetch = LAZY)
    private Credit credit;
    //будет создан раздел credit_id основанный на id из класса Credit,
    // по этому полу будет JOIN
    //-> владеющая сторона

    @OneToOne(cascade = {MERGE, PERSIST, REFRESH}, orphanRemoval = true)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @OneToMany(mappedBy = "account", fetch = LAZY,
            cascade = ALL)
    private List<Operation> operations;
    //указывает, что связь между таблицами будет установлена через поле account

    @OneToMany(mappedBy = "account", fetch = LAZY,
            orphanRemoval = true, cascade = {MERGE, PERSIST, REFRESH})
    private List<PaymentSchedule> paymentSchedules;

    @OneToOne(mappedBy = "account", cascade = ALL, fetch = LAZY)
    private Card card;

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

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", loanDebt=" + loanDebt +
                ", percentageDebt=" + percentageDebt +
                ", country='" + country + '\'' +
                ", status=" + status +
                ", balance=" + balance +
                ", openingDate=" + openingDate +
                ", lastUpdateDate=" + lastUpdateDate +
                ", closingDate=" + closingDate +
                ", unpaidCreditSum=" + unpaidCreditSum +
                ", currency='" + currency + '\'' +
                ", credit=" + credit +
                ", client=" + client +
                '}';
    }
}

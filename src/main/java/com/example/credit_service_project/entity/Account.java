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
    @NotNull(message = "accountNumber must not be null!")
    @NotBlank(message = "accountNumber must not be empty!")
    private String country;

    @NotNull(message = "status must not be null!")
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @NotNull(message = "balance must not be null!")
    @PositiveOrZero(message = "Balance must be greater than 0")
    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "opening_date")
    @NotNull(message = "openingDate must not be null!")
    private LocalDate openingDate;

    @Column(name = "closing_date")
    @NotNull(message = "closingDate must not be null!")
    private LocalDate closingDate;

    @PositiveOrZero(message = "unpaidPercentageLoanDebt must be greater than 0")
    @Column(name = "unpaid_load_debt")
    private BigDecimal unpaidLoanDebt;

    @PositiveOrZero(message = "unpaidPercentageLoanDebt must be greater than 0")
    @Column(name = "unpaid_percentage_load_debt")
    private BigDecimal unpaidPercentageLoanDebt;

    @NotNull(message = "Currency must not be null!")
    @Pattern(regexp = "^[A-Z]{3}$",message = "Format is not allowed in service!")
    @Column(name = "currency")
    private String currency;

    @OneToOne( mappedBy = "account",cascade = {MERGE, PERSIST, REFRESH}, fetch = FetchType.LAZY)
    private Credit credit;
    //будет создан раздел credit_id основанный на id из класса Credit,
    // по этому полу будет JOIN
    //-> владеющая сторона

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY,
            cascade = ALL)
    private List<Operation> operations;
    //указывает, что связь между таблицами будет установлена через поле account

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY,
            orphanRemoval = true, cascade = {MERGE, PERSIST, REFRESH})
    private List<PaymentSchedule> paymentSchedules;

    @OneToOne(mappedBy = "account", cascade = ALL, fetch = FetchType.EAGER)
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

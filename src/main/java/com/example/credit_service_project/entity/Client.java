package com.example.credit_service_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Entity
@Data
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    @NotNull(message = "Name must not be null!")
    @NotEmpty(message = "Name must not be empty!")
    private String name;

    @Column(name = "surname")
    @NotNull(message = "Surname must not be null!")
    @NotEmpty(message = "Surname must not be empty!")
    private String surname;

    @Column(name = "salary")
    @PositiveOrZero(message = "Income must not be negative!")
    private BigDecimal salary;

    @Column(name = "passive_income")
    @Positive(message = "Income must not be negative!")
    private BigDecimal passiveIncome;

    @Column(name = "expenses")
    @Positive(message = "Expenses must not be negative!")
    private BigDecimal expenses;

    @Column(name = "address")
    @NotNull(message = "address must not be null!")
    @NotEmpty(message = "address must not be empty!")
    private String address;

    @Column(name = "email")
    @Email(message = "must be email!")
    private String email;

    @Column(name = "phone")
    @Pattern(regexp = "^(?:(?:\\+\\d{1,3}\\s?)|(?:\\d{1,4}\\s?))?(?:\\d{4,14})$", message = "Number is not supported!")
    private String phone;

    @Column(name = "registration_date")
    @NotNull(message = "registrationDate must not be null!")
    private LocalDate registrationDate;

    @Column(name = "update_date")
    @NotNull(message = "updateDate must not be null!")
    private LocalDate updateDate;

    @OneToOne(mappedBy = "client", orphanRemoval = true,
            cascade = {MERGE, PERSIST, REFRESH})
    private Account account;

    @ManyToOne(cascade = {MERGE, PERSIST, REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private Manager manager;
}

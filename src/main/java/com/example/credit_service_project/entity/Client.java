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
    @NotBlank(message = "Name must not be blank!")
    private String name;

    @Column(name = "surname")
    @NotBlank(message = "Surname must not be blank!")
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
    @NotBlank(message = "Address must not be blank!")
    private String address;

    @Column(name = "email")
    @NotBlank(message = "email must not be blank!")
    @Email(message = "Format must be email!")
    private String email;

    @Column(name = "phone")
    @Pattern(regexp = "^(?:\\+\\d{1,3}\\s?)?(?:\\d{1,4}\\s?)?\\d{6,14}$", message = "Number is not supported!")
    private String phone;

    @Column(name = "registration_date")
    @NotBlank(message = "registrationDate must not be blank!")
    private LocalDate registrationDate;

    @Column(name = "update_date")
    @NotBlank(message = "registrationDate must not be blank!")
    private LocalDate updateDate;

    @OneToOne(mappedBy = "client", orphanRemoval = true,
            cascade = {MERGE, PERSIST, REFRESH})
    private Account account;

    @ManyToOne(cascade = {MERGE, PERSIST, REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private Manager manager;
}

package com.example.credit_service_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "clients")
public class User {
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

    @Column(name = "password")
    @NotBlank(message = "Password must not be blank!")
    private String password;

    @Column(name = "salary")
    @PositiveOrZero(message = "Income must not be negative!")
    private BigDecimal salary;

    @Column(name = "passive_income")
    @PositiveOrZero(message = "Income must not be negative!")
    private BigDecimal passiveIncome;

    @Column(name = "expenses")
    @Positive(message = "Expenses must not be negative!")
    private BigDecimal expenses;

    @Column(name = "address")
    @NotBlank(message = "Address must not be blank!")
    private String address;

    @Column(name = "email", unique = true)
    @NotBlank(message = "email must not be blank!")
    @Email(message = "Format must be email!")
    private String email;

    @Column(name = "phone")
    @Pattern(regexp = "^(?:\\+\\d{1,3}\\s?)?\\d{1,4}\\s?\\d{2,3}\\s?\\d{3,9}$", message = "Number is not supported!")
    private String phone;

    @Column(name = "registration_date")
    @NotNull(message = "registrationDate must not be null!")
    private LocalDate registrationDate;

    @Column(name = "update_date")
    @NotNull(message = "registrationDate must not be null!")
    private LocalDate updateDate;

    @Column(name = "role")
    @ManyToOne(cascade = {MERGE, PERSIST, REFRESH}, fetch = FetchType.EAGER)
    private Role role;

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", salary=" + salary +
                ", passiveIncome=" + passiveIncome +
                ", expenses=" + expenses +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", registrationDate=" + registrationDate +
                ", updateDate=" + updateDate +
                '}';
    }

}

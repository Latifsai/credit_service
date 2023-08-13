package com.example.credit_service_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
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

    @Column(name = "income")
    @Positive(message = "Income must not be negative!")
    private BigDecimal income;

    @Column(name = "expenses")
    @Positive(message = "Expenses must not be negative!")
    private BigDecimal expenses;

    @OneToOne(mappedBy = "client", orphanRemoval = true, cascade = {MERGE, PERSIST, REFRESH})
    private Account account;

    @ManyToOne(cascade = {MERGE, PERSIST, REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private Manager manager;
}

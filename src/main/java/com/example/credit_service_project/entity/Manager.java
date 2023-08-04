package com.example.credit_service_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Data
@Entity
@Table(name = "managers")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    @NotNull(message = "Name must not be null!")
    private String name;

    @Column(name = "surname")
    @NotNull(message = "Surname must not be null!")
    private String surname;

    @Column(name = "e-mail")
    @Email
    private String email;

    @OneToMany(mappedBy = "manager", fetch = FetchType.EAGER, cascade = {MERGE, PERSIST, REFRESH})
    private List<Client> clients;
}

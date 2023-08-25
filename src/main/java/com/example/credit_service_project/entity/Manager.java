package com.example.credit_service_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
    @NotBlank(message = "Name must not be blank!")
    private String name;

    @Column(name = "surname")
    @NotBlank(message = "Surname must not be blank!")
    private String surname;

    @Column(name = "e-mail")
    @Email(message = "email must match the format of the email")
    @NotBlank(message = "Email must not be blank!")
    private String email;

    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY, cascade = {MERGE, PERSIST, REFRESH})
    private List<Client> clients;
}

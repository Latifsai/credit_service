package com.example.credit_service_project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Delay {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "time_of_delay")
    @NotNull(message = "timeOfDelay mist not be null!")
    private LocalDateTime timeOfDelay;

    @Column(name = "sum_of_delay")
    @NotNull(message = "sumOfDelay must no be null!")
    private BigDecimal sumOfDelay;
}

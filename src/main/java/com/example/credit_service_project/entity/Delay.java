package com.example.credit_service_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
@Table(name = "delays")
public class Delay {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "time_of_delay")
    @NotNull(message = "timeOfDelay mist not be null!")
    private LocalDateTime timeOfDelay;

    @Column(name = "sum_of_delay")
    @NotNull(message = "sumOfDelay must no be null!")
    @Positive(message = "sumOfDelay must not be zero or positive")
    private BigDecimal sumOfDelay;

    @ManyToOne(cascade = {MERGE, PERSIST, REFRESH}, fetch = LAZY)
    @JoinColumn(name = "credit_history_id", referencedColumnName = "id")
    private CreditHistory creditHistory;
}

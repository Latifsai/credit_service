package com.example.credit_service_project.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.*;

import static jakarta.persistence.CascadeType.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "agreements")
public class Agreement {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "number")
    @Size(min = 6, max = 20, message = "number must be between 6 and 20!")
    @NotBlank(message = "number must not be blank")
    private String number;

    @Column(name = "agreement_date")
    @NotBlank(message = "AgreementDate must not be blank!")
    private LocalDate agreementDate;

    @Column(name = "termination_date")
    private LocalDate terminationDate;

    @Column(name = "active")
    private boolean active;

    @OneToOne(mappedBy = "agreement",cascade = {MERGE, PERSIST, REFRESH},
            fetch = FetchType.LAZY)
    private Credit credit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agreement agreement = (Agreement) o;
        return Objects.equals(number, agreement.number) && Objects.equals(credit, agreement.credit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, credit);
    }
}

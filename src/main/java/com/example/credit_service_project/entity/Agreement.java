package com.example.credit_service_project.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    @NotNull(message = "AgreementDate must not be null!")
    private LocalDate agreementDate;

    @Column(name = "termination_date")
    private LocalDate terminationDate;

    @Column(name = "active")
    private boolean active;

    @Column(name = "credit_order_number")
    @NotBlank(message = "creditOrderNumber must not be blank!")
    private String creditOrderNumber;

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

    @Override
    public String toString() {
        return "Agreement{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", agreementDate=" + agreementDate +
                ", terminationDate=" + terminationDate +
                ", active=" + active +
                '}';
    }
}

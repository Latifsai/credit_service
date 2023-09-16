package com.example.credit_service_project.entity;

import com.example.credit_service_project.entity.enums.CreditHistoryStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "credit_histories")
public class CreditHistory {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status must not be null!")
    private CreditHistoryStatus status;

    @OneToMany(mappedBy = "creditHistory",cascade = {MERGE, PERSIST, REFRESH}, fetch = LAZY)
    private List<Delay> delays;

    @OneToOne(cascade = {MERGE, PERSIST, REFRESH}, fetch = LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditHistory that = (CreditHistory) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CreditHistory{" +
                "id=" + id +
                ", status=" + status +
                ", delays=" + delays +
                '}';
    }
}

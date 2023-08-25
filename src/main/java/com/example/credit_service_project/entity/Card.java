package com.example.credit_service_project.entity;

import com.example.credit_service_project.entity.enums.CardStatus;
import com.example.credit_service_project.entity.enums.PaymentSystem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
@Table(name = "cards")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "card_number")
    @NotBlank(message = "accountNumber must not be blank!")
    @Size(min = 7, max = 12, message = "cardNumber must be between 5 and 16!")
    private String cardNumber;

    @Column(name = "iban")
    @NotBlank(message = "IBAN must not be blank!")
    private String IBAN;

    @Column(name = "holder_name")
    @NotBlank(message = "holderName must not be blank!")
    private String holderName;

    @Column(name = "opening_date")
    @NotBlank(message = "openingDate must not be blank!")
    private LocalDate openingDate;

    @Column(name = "expiration_date")
    @NotBlank(message = "expirationDate must not be blank!")
    private LocalDate expirationDate;

    @Column(name = "balance")
    @PositiveOrZero(message = "Balance must be o or greater!")
    private BigDecimal balance; // ✅

    @Column(name = "delivery_address")
    @NotBlank(message = "deliveryAddress must not be blank!")
    private String deliveryAddress; // ✅

    @Column(name = "is_digital_valet")
    private boolean isDigitalValet;

    @Column(name = "payment_system")
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "paymentSystem must not be blank!")
    private PaymentSystem paymentSystem;

    @Column(name = "card_status")
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "cardStatus must not be blank!")
    private CardStatus cardStatus;  // ✅

    @OneToOne(cascade = ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(cardNumber, card.cardNumber) && Objects.equals(account, card.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, account);
    }
}

package com.example.credit_service_project.services.utils;

import com.example.credit_service_project.dto.cardDTO.CreateCardRequestDTO;
import com.example.credit_service_project.dto.cardDTO.CardResponseDTO;
import com.example.credit_service_project.dto.cardDTO.UpdateCardRequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.entity.enums.CardStatus;
import com.example.credit_service_project.services.utils.generator.CardGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CardUtil {

    public Card convertCreateRequestToEntity(CreateCardRequestDTO request, Account account) {
        Card card = new Card();
        card.setAccount(account);
        card.setCardNumber(CardGenerator.generateCardData(12, false));
        card.setHolderName(account.getUser().getName() + " " + account.getUser().getSurname());
        card.setIBAN(CardGenerator.getIBAN(account.getCountry()));
        card.setOpeningDate(LocalDate.now());
        card.setExpirationDate(CardGenerator.setExpirationDate(card.getOpeningDate(), request.getYearAccessibility()));
        card.setBalance(account.getBalance());
        card.setDeliveryAddress(account.getUser().getAddress());
        card.setDigitalValet(request.isDigitalValet());
        card.setPaymentSystem(request.getPaymentSystem());
        card.setCardStatus(CardStatus.ACTIVE);
        return card;
    }

    public CardResponseDTO convertCardToAddDTOResponse(Card card) {
        return CardResponseDTO.builder()
                .id(card.getId())
                .cardNumber(card.getCardNumber())
                .holderName(card.getHolderName())
                .IBAN(card.getIBAN())
                .balance(card.getBalance())
                .paymentSystem(card.getPaymentSystem())
                .cardStatus(card.getCardStatus())
                .accountNumber(card.getAccount().getAccountNumber())
                .expirationDate(card.getExpirationDate())
                .currency(card.getAccount().getCurrency())
                .build();
    }

    public Card updateCard(Card card, UpdateCardRequest request) {
        if (request.getBalance() != null) card.setBalance(request.getBalance());
        if (request.getDeliveryAddress() != null && !request.getDeliveryAddress().trim().isEmpty())
            card.setDeliveryAddress(request.getDeliveryAddress());
        if (request.getCardStatus() != null) card.setCardStatus(request.getCardStatus());
        return card;
    }

    public Account updateAccountBalance(Account account, Card card){
        if (!account.getBalance().equals(card.getBalance())) {
            account.setBalance(card.getBalance());
        }
        return account;
    }
}

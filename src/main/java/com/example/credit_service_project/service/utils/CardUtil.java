package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.DTO.cardDTO.AddCardDTORequest;
import com.example.credit_service_project.DTO.cardDTO.CardDTOResponse;
import com.example.credit_service_project.DTO.cardDTO.UpdateCardDTORequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.entity.enums.CardStatus;
import com.example.credit_service_project.generator.CardGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CardUtil {

    public Card convertAddRequestToEntity(AddCardDTORequest request, Account account) {
        Card card = new Card();
        card.setAccount(account);
        card.setCardNumber(CardGenerator.generateCardNumber());
        card.setHolderName(account.getClient().getName() + " " + account.getClient().getSurname());
        card.setIBAN(CardGenerator.getIBAN(account.getCountry()));
        card.setOpeningDate(LocalDate.now());
        card.setExpirationDate(CardGenerator.setExpirationDate(card.getOpeningDate(), request.getYearAccessibility()));
        card.setBalance(account.getBalance());
        card.setDeliveryAddress(request.getDeliveryAddress());
        card.setDigitalValet(request.isDigitalValet());
        card.setPaymentSystem(request.getPaymentSystem());
        card.setCardStatus(CardStatus.ACTIVE);
        return card;
    }

    public CardDTOResponse convertCardToAddDTOResponse(Card card) {
        return new CardDTOResponse(
                card.getId(),
                card.getCardNumber(),
                card.getHolderName(),
                card.getIBAN(),
                card.getBalance(),
                card.getPaymentSystem(),
                card.getCardStatus(),
                card.getAccount().getAccountNumber(),
                card.getExpirationDate(),
                card.getAccount().getCurrency());
    }

    public Card updateCard(Card card, UpdateCardDTORequest request) {
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

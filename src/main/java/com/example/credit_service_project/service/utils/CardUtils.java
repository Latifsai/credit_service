package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.DTO.cardDTO.*;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.entity.enums.CardStatus;
import com.example.credit_service_project.generator.CardGenerator;
import org.springframework.stereotype.Component;

@Component
public class CardUtils {

    public Card convertAddRequestToEntity(AddCardDTORequest request, Account account) {
        Card card = new Card();
        card.setAccount(account);
        card.setCardNumber(CardGenerator.generateCardNumber());
        card.setHolderName(request.getHolderName());
        card.setOpeningDate(request.getOpeningDate());
        card.setExpirationDate(CardGenerator.setExpirationDate(card.getOpeningDate(), request.getYearAccessibility()));
        card.setBalance(account.getBalance());
        card.setTransactionLimit(5);
        card.setDeliveryAddress(request.getDeliveryAddress());
        card.setDigitalValet(request.isDigitalValet());
        card.setPaymentSystem(request.getPaymentSystem());
        card.setCardStatus(CardStatus.ACTIVE);

//        account.setCard(card);
        return card;
    }

    public AddedAndSearchCardDTOResponse convertCardToAddResponse(Card card) {
        return new AddedAndSearchCardDTOResponse(
                card.getId(),
                card.getCardNumber(),
                card.getHolderName(),
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

    public UpdateCardDTOResponse convertCardToUpdateResponse(Card card) {
        return new UpdateCardDTOResponse(
                card.getId(),
                card.getCardNumber(),
                card.getHolderName(),
                card.getBalance(),
                card.getDeliveryAddress(),
                card.getCardStatus());
    }

    public GetCardsResponse convertCardToGetCardResponse(Card card) {
        var response = new GetCardsResponse();
        response.setId(card.getId());
        response.setCardNumber(card.getCardNumber());
        response.setHolderName(card.getHolderName());
        response.setExpirationDate(card.getExpirationDate());
        response.setBalance(card.getBalance());
        response.setDeliveryAddress(card.getDeliveryAddress());
        response.setDigitalValet(card.isDigitalValet());
        response.setPaymentSystem(card.getPaymentSystem());
        response.setCardStatus(card.getCardStatus());
        return response;
    }
}

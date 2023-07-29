package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.DTO.cardDTO.AddCardDTORequest;
import com.example.credit_service_project.DTO.cardDTO.AddedAndSearchCardDTOResponse;
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
                card.getAccount().getCard().getExpirationDate(),
                card.getAccount().getCurrency());
    }
}

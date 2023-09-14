package com.example.credit_service_project.services.utils;

import com.example.credit_service_project.dto.cardDTO.CardResponseDTO;
import com.example.credit_service_project.dto.cardDTO.CreateCardRequestDTO;
import com.example.credit_service_project.dto.cardDTO.UpdateCardRequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.entity.enums.PaymentSystem;
import com.example.credit_service_project.services.generators.DTOCardCreator;
import com.example.credit_service_project.services.generators.EntityCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CardUtilTest {

    @Mock
    private CardUtil util;
    @Test
    void convertCreateRequestToEntity() {
        CreateCardRequestDTO request = new CreateCardRequestDTO(UUID.randomUUID(), null, 5,
                true, PaymentSystem.VISA);
        Account account = EntityCreator.getAccount();
        Card card = EntityCreator.getCard();

        when(util.convertCreateRequestToEntity(request, account)).thenReturn(card);
        assertEquals(card, util.convertCreateRequestToEntity(request, account));
    }

    @Test
    void convertCardToAddDTOResponse() {
        CardResponseDTO responseDTO = DTOCardCreator.getCardResponse();
        Card card = EntityCreator.getCard();

        when(util.convertCardToAddDTOResponse(card)).thenReturn(responseDTO);
        assertEquals(responseDTO, util.convertCardToAddDTOResponse(card));
    }

    @Test
    void updateCard() {
        UpdateCardRequest request = new UpdateCardRequest(UUID.randomUUID(), BigDecimal.valueOf(3000), null,null);
        Card card = EntityCreator.getCard();
        Card updatedCard = EntityCreator.getUpdatedCard();

        when(util.updateCard(card, request)).thenReturn(updatedCard);

        Card result = util.updateCard(card, request);
        assertEquals(updatedCard, result);
    }

    @Test
    void updateAccountBalance() {
        Card card = EntityCreator.getCard();
        Account account = EntityCreator.getAccount();
        Account updatedAccount = EntityCreator.getUpgratedAccount();

        when(util.updateAccountBalance(account, card)).thenReturn(account);

        assertEquals(updatedAccount, util.updateAccountBalance(account, card));
    }
}
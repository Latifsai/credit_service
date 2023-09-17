package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.dto.cardDTO.CreateCardRequestDTO;
import com.example.credit_service_project.dto.cardDTO.UpdateCardRequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.entity.enums.PaymentSystem;
import com.example.credit_service_project.generators.EntityCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CardUtilTest {

    @InjectMocks
    private CardUtil util;
    @Test
    @DisplayName("Test convertCreateRequestToEntity method")
    void convertCreateRequestToEntity() {
        CreateCardRequestDTO request = new CreateCardRequestDTO(UUID.randomUUID(), null, 5,
                true, PaymentSystem.VISA);
        Account account = EntityCreator.getAccount();

        assertNotNull(util.convertCreateRequestToEntity(request, account));
    }

    @Test
    @DisplayName("Test convertCardToAddDTOResponse method")
    void convertCardToAddDTOResponse() {
        Card card = EntityCreator.getCard();
        card.setAccount(EntityCreator.getAccount());
        assertNotNull( util.convertCardToAddDTOResponse(card));
    }

    @Test
    @DisplayName("Test updateCard method")
    void updateCard() {
        UpdateCardRequest request = new UpdateCardRequest(UUID.randomUUID(), BigDecimal.valueOf(3000), null,null);
        Card card = EntityCreator.getCard();

        Card result = util.updateCard(card, request);
        assertNotNull(result);
    }

    @Test
    @DisplayName("Test updateAccountBalance method")
    void updateAccountBalance() {
        Card card = EntityCreator.getCard();
        Account account = EntityCreator.getAccount();
        Account updatedAccount = EntityCreator.getUpgratedAccount();


        assertEquals(updatedAccount, util.updateAccountBalance(account, card));
    }
}
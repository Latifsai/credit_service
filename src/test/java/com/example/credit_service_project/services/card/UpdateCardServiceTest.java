package com.example.credit_service_project.services.card;

import com.example.credit_service_project.DTO.cardDTO.CardResponseDTO;
import com.example.credit_service_project.DTO.cardDTO.UpdateCardRequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.services.account.AccountUpdateService;
import com.example.credit_service_project.services.generators.DTOCardCreator;
import com.example.credit_service_project.services.generators.EntityCreator;
import com.example.credit_service_project.services.utils.CardUtil;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateCardServiceTest {

    @Mock
    private CardSearchService searchCardService;
    @Mock
    private CardCreateService cardCreateService;
    @Mock
    private AccountUpdateService updateAccountService;
    @Mock
    private CardUtil utils;
    @InjectMocks
    private CardUpdateService updateService;

    @Test
    public void testUpdateSuccess() {
        Account account = EntityCreator.getAccount();
        Card card = EntityCreator.getCard();
        card.setAccount(account);
        Card updatedCard = EntityCreator.getUpdatedCard();

        UpdateCardRequest request = new UpdateCardRequest(UUID.randomUUID(), new BigDecimal("5000"),
                "", null);

        var response = DTOCardCreator.getUpdatedCardResponse();

        when(searchCardService.findCardById(request.getId())).thenReturn(card);
        when(utils.updateCard(card, request)).thenReturn(EntityCreator.getUpdatedCard());
        when(cardCreateService.saveCard(card)).thenReturn(card);
        when(utils.convertCardToAddDTOResponse(updatedCard)).thenReturn(response);
        when(utils.updateAccountBalance(account, card)).thenReturn(account);
        when(updateAccountService.saveUpdatedAccount(account)).thenReturn(account);

        CardResponseDTO actual = updateService.updateCard(request);
        assertNotNull(actual);
        assertEquals(response, actual);
    }

    @Test
    public void testUpdateNotFoundException() {
        UpdateCardRequest request = new UpdateCardRequest(UUID.randomUUID(), new BigDecimal("5000"),
                "", null);

        when(searchCardService.findCardById(request.getId())).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> updateService.updateCard(request));
    }
}
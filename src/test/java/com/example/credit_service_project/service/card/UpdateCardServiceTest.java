package com.example.credit_service_project.service.card;

import com.example.credit_service_project.dto.cardDTO.CardResponseDTO;
import com.example.credit_service_project.dto.cardDTO.UpdateCardRequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.service.account.AccountUpdateService;
import com.example.credit_service_project.generators.CardDTOGenerator;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.service.utils.CardUtil;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("Test update card method")
    public void updateCard() {
        Account account = EntityCreator.getAccount();
        Card card = EntityCreator.getCard();
        card.setAccount(account);
        Card updatedCard = EntityCreator.getUpdatedCard();

        UpdateCardRequest request = new UpdateCardRequest(UUID.randomUUID(), new BigDecimal("5000"),"", null);

        var response = CardDTOGenerator.getUpdatedCardResponse();

        when(searchCardService.findCardById(request.getId())).thenReturn(card);
        when(utils.updateCard(card, request)).thenReturn(EntityCreator.getUpdatedCard());
        when(cardCreateService.saveCard(card)).thenReturn(card);
        when(utils.convertCardToAddDTOResponse(updatedCard)).thenReturn(response);
        when(utils.updateAccountBalance(account, card)).thenReturn(account);
        when(updateAccountService.saveUpdatedAccount(account)).thenReturn(account);

        CardResponseDTO actual = updateService.saveUpdateCard(request);

        assertNotNull(actual);
        assertEquals(response, actual);
        verify(searchCardService, times(1)).findCardById(request.getId());
        verify(utils, times(1)).updateCard(card, request);
        verify(cardCreateService, times(1)).saveCard(card);
        verify(utils, times(1)).convertCardToAddDTOResponse(updatedCard);
        verify(utils, times(1)).updateAccountBalance(account, card);
        verify(updateAccountService, times(1)).saveUpdatedAccount(account);

    }

    @Test
    @DisplayName("Test update card method throws NotFoundException")
    public void updateNotFoundException() {
        UpdateCardRequest request = new UpdateCardRequest(UUID.randomUUID(), new BigDecimal("5000"), "", null);

        when(searchCardService.findCardById(request.getId())).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> updateService.saveUpdateCard(request));
    }


    @Test
    @DisplayName("Test save updated card method")
    public void saveUpdateCard() {
        Card card = EntityCreator.getCard();

        when(cardCreateService.saveCard(card)).thenReturn(card);
        assertEquals(card, cardCreateService.saveCard(card));
        verify(cardCreateService, times(1)).saveCard(card);
    }
}
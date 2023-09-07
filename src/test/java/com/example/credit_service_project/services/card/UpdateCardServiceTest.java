package com.example.credit_service_project.services.card;

import com.example.credit_service_project.DTO.cardDTO.CardResponseDTO;
import com.example.credit_service_project.DTO.cardDTO.UpdateCardDTORequest;
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
import static org.mockito.Mockito.when;

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
        Card card = EntityCreator.getCard();
        Account account = card.getAccount();
        UpdateCardDTORequest request = new UpdateCardDTORequest(UUID.randomUUID(), new BigDecimal("5000"),
                "", null);

        when(searchCardService.findCardById(request.getId())).thenReturn(card);
        when(utils.updateCard(card, request)).thenReturn(EntityCreator.getUpdatedCard());
        when(utils.convertCardToAddDTOResponse(EntityCreator.getUpdatedCard()))
                .thenReturn(DTOCardCreator.getUpdatedCardResponse());
        when(utils.updateAccountBalance(account, card)).thenReturn(account);
        CardResponseDTO actual = updateService.updateCard(request);
        assertNotNull(actual);
        assertEquals(DTOCardCreator.getUpdatedCardResponse(), actual);
    }

    @Test
    public void testUpdateNotFoundException() {
        UpdateCardDTORequest request = new UpdateCardDTORequest(UUID.randomUUID(), new BigDecimal("5000"),
                "", null);

        when(searchCardService.findCardById(request.getId())).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> updateService.updateCard(request));
    }
}
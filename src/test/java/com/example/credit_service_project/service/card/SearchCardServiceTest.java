package com.example.credit_service_project.service.card;

import com.example.credit_service_project.dto.cardDTO.CardResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.entity.enums.CardStatus;
import com.example.credit_service_project.generators.CardDTOGenerator;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.repository.CardRepository;
import com.example.credit_service_project.service.utils.CardUtil;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchCardServiceTest {

    @Mock
    private CardRepository repository;
    @Mock
    private CardUtil utils;
    @InjectMocks
    private CardSearchService service;

    private final UUID id = UUID.randomUUID();
    private final Card card = EntityCreator.getCard();
    private final Account account = EntityCreator.getAccount();


    @Test
    @DisplayName("Test search card method")
    public void searchCard() {
        when(repository.findById(id)).thenReturn(Optional.of(card));
        when(utils.convertCardToAddDTOResponse(card)).thenReturn(CardDTOGenerator.getCardResponse());

        CardResponseDTO response = service.searchCard(id);

        assertNotNull(response);
        assertEquals(CardDTOGenerator.getCardResponse(), response);
        verify(repository, times(1)).findById(id);
        verify(utils, times(1)).convertCardToAddDTOResponse(card);
    }

    @Test
    @DisplayName("Test search card method throws NotFoundException")
    public void searchCardNotFoundException() {
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.searchCard(id));
    }

    @Test
    @DisplayName("Test find card by ID method")
    public void findCardById() {
        when(repository.findById(id)).thenReturn(Optional.of(card));
        assertEquals(Optional.of(card), repository.findById(id));
        verify(repository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Test find card by ID method thorws NotFoundException")
    public void findCardByIdNotFoundException() {
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertEquals(Optional.empty(), repository.findById(id));
        verify(repository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Test find by account method")
    public void findByAccount() {
        when(repository.findByAccount(account)).thenReturn(List.of(card));

        Card result = service.findByAccount(account);

        assertNotNull(result);
        assertEquals(CardStatus.ACTIVE, result.getCardStatus());
        verify(repository, times(1)).findByAccount(account);
    }

    @Test
    @DisplayName("Test find by account method throws NotFoundException ")
    public void findByAccountNotFound() {
        when(repository.findByAccount(account)).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> repository.findByAccount(account));
    }

}
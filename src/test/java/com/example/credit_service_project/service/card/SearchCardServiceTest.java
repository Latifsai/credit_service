package com.example.credit_service_project.service.card;

import com.example.credit_service_project.dto.cardDTO.CardResponseDTO;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.repository.CardRepository;
import com.example.credit_service_project.generators.CardDTOGenerator;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.service.utils.CardUtil;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchCardServiceTest {

    @Mock
    private CardRepository repository;
    @Mock
    private CardUtil utils;
    @InjectMocks
    private CardSearchService service;

    @Test
    public void testSearchCard() {
        UUID id = UUID.randomUUID();
        Card card = EntityCreator.getCard();

        when(repository.findById(id)).thenReturn(Optional.of(card));
        when(utils.convertCardToAddDTOResponse(card)).thenReturn(CardDTOGenerator.getCardResponse());

        CardResponseDTO response = service.searchCard(id);

        assertNotNull(response);
        assertEquals(CardDTOGenerator.getCardResponse(), response);
    }

    @Test
    public void testSearchNotFoundException() {
        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.searchCard(id));
    }

}
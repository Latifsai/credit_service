package com.example.credit_service_project.service.card;

import com.example.credit_service_project.dto.cardDTO.CardResponseDTO;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.repository.CardRepository;
import com.example.credit_service_project.generators.CardDTOGenerator;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.service.utils.CardUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetCardsServiceTest {

    @Mock
    private CardRepository repository;
    @Mock
    private CardUtil utils;
    @InjectMocks
    private GetAllCardsService getAllCardsService;

    @Test
    @DisplayName(value = "Test get cards method")
    public void getCards() {
        List<Card> cards = List.of(EntityCreator.getCard());
        List<CardResponseDTO> expected = List.of(CardDTOGenerator.getCardResponse());

        when(repository.findAll()).thenReturn(cards);
        when(utils.convertCardToAddDTOResponse(cards.get(0))).thenReturn(expected.get(0));

        var response = getAllCardsService.getAllCards();

        assertEquals(expected, response);
        verify(repository, times(1)).findAll();
        verify(utils, times(1)).convertCardToAddDTOResponse(cards.get(0));
    }

    @Test
    @DisplayName("Test get cards emptyList method")
    public void testGetCardsEmptyList() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        assertEquals(Collections.emptyList(),getAllCardsService.getAllCards());
        verify(repository, times(1)).findAll();
    }

}
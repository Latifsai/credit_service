package com.example.credit_service_project.services.card;

import com.example.credit_service_project.dto.cardDTO.CardResponseDTO;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.repositories.CardRepository;
import com.example.credit_service_project.generators.CardDTOGenerator;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.services.utils.CardUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetCardsServiceTest {

    @Mock
    private CardRepository repository;

    @Mock
    private CardUtil utils;

    @InjectMocks
    private GetAllCardsService getAllCardsService;

    @Test
    public void testGetCardsSuccess() {
        List<Card> cards = List.of(EntityCreator.getCard());
        List<CardResponseDTO> expected = List.of(CardDTOGenerator.getCardResponse());

        when(repository.findAll()).thenReturn(cards);
        when(utils.convertCardToAddDTOResponse(cards.get(0))).thenReturn(expected.get(0));

        var response = getAllCardsService.getAllCards();

        assertEquals(expected, response);
    }

    @Test
    public void testGetCardsEmptyListException() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        assertEquals(Collections.emptyList(),getAllCardsService.getAllCards());
    }

}
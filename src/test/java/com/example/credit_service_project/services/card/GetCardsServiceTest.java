package com.example.credit_service_project.services.card;

import com.example.credit_service_project.repositories.CardRepository;
import com.example.credit_service_project.services.generators.DTOCardCreator;
import com.example.credit_service_project.services.generators.EntityCreator;
import com.example.credit_service_project.services.utils.CardUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetCardsServiceTest {

    @Spy
    private CardRepository repository;

    @Mock
    private CardUtil utils;

    @InjectMocks
    private GetAllCardsService serviceImp;

    @Test
    public void testGetCardsSuccess() {
        var cards = List.of(EntityCreator.getCard());
        var expected = DTOCardCreator.getListResponse();

        when(repository.findAll()).thenReturn(cards);

        when(utils.convertCardToAddDTOResponse(cards.get(0))).thenReturn(expected.get(0));

        var response = serviceImp.getAllCards();

        assertEquals(expected, response);
    }

    @Test
    public void testGetCardsEmptyListException() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        assertEquals(Collections.emptyList(),serviceImp.getAllCards());
    }

}
package com.example.credit_service_project.serviceTest.cardTest;

import com.example.credit_service_project.repository.CardRepository;
import com.example.credit_service_project.service.card.GetCardsServiceImp;
import com.example.credit_service_project.service.utils.CardUtil;
import com.example.credit_service_project.serviceTest.generators.DTOCardCreator;
import com.example.credit_service_project.serviceTest.generators.EntityCreator;
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
class GetCardsServiceImpTest {

    @Spy
    private CardRepository repository;

    @Mock
    private CardUtil utils;

    @InjectMocks
    private GetCardsServiceImp serviceImp;

    @Test
    public void testGetCardsSuccess() {
        var cards = List.of(EntityCreator.getCard());
        var expected = DTOCardCreator.getListResponse();

        when(repository.findAll()).thenReturn(cards);

        when(utils.convertCardToAddDTOResponse(cards.get(0))).thenReturn(expected.get(0));

        var response = serviceImp.execute();

        assertEquals(expected, response);
    }

    @Test
    public void testGetCardsEmptyListException() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        assertEquals(Collections.emptyList(),serviceImp.execute());
    }

}
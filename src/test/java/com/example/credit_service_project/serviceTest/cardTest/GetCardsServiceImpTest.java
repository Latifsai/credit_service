package com.example.credit_service_project.serviceTest.cardTest;

import com.example.credit_service_project.DTO.cardDTO.GetCardsRequest;
import com.example.credit_service_project.DTO.cardDTO.GetCardsResponse;
import com.example.credit_service_project.repository.CardRepository;
import com.example.credit_service_project.service.card.GetCardsServiceImp;
import com.example.credit_service_project.service.errors.exceptions.EmptyListException;
import com.example.credit_service_project.serviceTest.generators.DTOCardCreator;
import com.example.credit_service_project.serviceTest.generators.EntityCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetCardsServiceImpTest {

    @Spy
    CardRepository repository;

    @InjectMocks
    GetCardsServiceImp serviceImp;
    @Test
    public void testGetCardsSuccess() {
        var request = new GetCardsRequest("A10B3U3OI9");
        var cards = List.of(EntityCreator.getCard());
        when(repository.findAllByAccount_AccountNumber(request.getAccountNumber())).thenReturn(cards);
        var response = serviceImp.execute(request);
        var expected = DTOCardCreator.getResponse();

        assertNotNull(request);
        assertEquals(expected, response);
    }

    @Test
    public void testGetCardsEmptyListException() {
        var request = new GetCardsRequest("A10B3U3OI9");
        when(repository.findAllByAccount_AccountNumber(request.getAccountNumber())).thenReturn(Collections.emptyList());
        assertThrows(EmptyListException.class, () -> serviceImp.execute(request));

    }

}
package com.example.credit_service_project.serviceTest.cardTest;

import com.example.credit_service_project.DTO.cardDTO.SearchCardDTOCreditRequest;
import com.example.credit_service_project.repository.CardRepository;
import com.example.credit_service_project.service.card.SearchCardServiceImp;
import com.example.credit_service_project.service.errors.exceptions.NotFoundException;
import com.example.credit_service_project.service.utils.CardUtil;
import com.example.credit_service_project.serviceTest.generators.DTOCardCreator;
import com.example.credit_service_project.serviceTest.generators.EntityCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchCardServiceImpTest {

    @Mock
    CardRepository repository;
    @Mock
    CardUtil utils;

    @InjectMocks
    SearchCardServiceImp service;
    @Test
    public void testSearchSuccess() {
        var request = new SearchCardDTOCreditRequest("A10B3U3OI9", "Oleg Kirilov");
        var card = EntityCreator.getCard();
        when(repository.findByCardNumberAndHolderName(request.getCardNumber(), request.getHolderName()))
                .thenReturn(Optional.of(card));
        when(utils.convertCardToAddDTOResponse(card)).thenReturn(DTOCardCreator.getAddResponse());

        var response = service.execute(request);

        assertNotNull(response);
        assertEquals(DTOCardCreator.getAddResponse(), response);
    }

    @Test
    public void testSearchNotFoundException() {
        var request = new SearchCardDTOCreditRequest("A10B3U3OI9", "Oleg Kirilov");
        when(repository.findByCardNumberAndHolderName(request.getCardNumber(), request.getHolderName()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.execute(request));

    }

}
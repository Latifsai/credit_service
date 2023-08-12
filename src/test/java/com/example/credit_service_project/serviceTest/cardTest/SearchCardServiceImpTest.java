package com.example.credit_service_project.serviceTest.cardTest;

import com.example.credit_service_project.repository.CardRepository;
import com.example.credit_service_project.service.card.SearchCardServiceImp;
import com.example.credit_service_project.service.utils.CardUtil;
import com.example.credit_service_project.serviceTest.generators.DTOCardCreator;
import com.example.credit_service_project.serviceTest.generators.EntityCreator;
import jakarta.validation.Validation;
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
class SearchCardServiceImpTest {

    @Mock
    private CardRepository repository;

    @Mock
    private CardUtil utils;

    @InjectMocks
    private SearchCardServiceImp service;

    @Test
    public void testSearchSuccess() {
        var request = new SearchCardDTOCreditRequest(UUID.randomUUID(), "A10B3U3OI9");
        var card = EntityCreator.getCard();

        when(repository.findByIdAndCardNumber(request.getId(), request.getCardNumber()))
                .thenReturn(Optional.of(card));

        when(utils.convertCardToAddDTOResponse(card)).thenReturn(DTOCardCreator.getCardResponse());

        var response = service.execute(request);

        assertNotNull(response);
        assertEquals(DTOCardCreator.getCardResponse(), response);
    }

    @Test
    public void testSearchNotFoundException() {
        var request = new SearchCardDTOCreditRequest(UUID.randomUUID(), "A10B3U3OI9");
        when(repository.findByIdAndCardNumber(request.getId(), request.getCardNumber()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.execute(request));
    }

    @Test
    public void testSearchValidation() {
        var request = new SearchCardDTOCreditRequest(null, "");

        var validator = Validation.buildDefaultValidatorFactory().getValidator();
        var set = validator.validate(request);
        assertEquals(2, set.size());
    }
}
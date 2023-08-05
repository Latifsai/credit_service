package com.example.credit_service_project.serviceTest.cardTest;

import com.example.credit_service_project.DTO.cardDTO.UpdateCardDTORequest;
import com.example.credit_service_project.repository.CardRepository;
import com.example.credit_service_project.service.card.UpdateCardServiceImp;
import com.example.credit_service_project.service.errors.exceptions.NotFoundException;
import com.example.credit_service_project.service.utils.CardUtil;
import com.example.credit_service_project.serviceTest.generators.DTOCardCreator;
import com.example.credit_service_project.serviceTest.generators.EntityCreator;
import jakarta.validation.Validation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateServiceExceptionImpTest {

    @Mock
    private CardRepository repository;

    @Mock
    private CardUtil utils;

    @InjectMocks
    private UpdateCardServiceImp updateService;

    @Test
    public void testUpdateSuccess() {
        var card = EntityCreator.getCard();
        var request = new UpdateCardDTORequest(UUID.fromString("00009999-2222-1111-a456-426655440000"),
                new BigDecimal("5000"), "", null);

        when(repository.findById(request.getId())).thenReturn(Optional.of(card));

        when(utils.updateCard(card, request)).thenReturn(EntityCreator.getUpdatedCard());

        when(utils.convertCardToAddDTOResponse(EntityCreator.getUpdatedCard()))
                .thenReturn(DTOCardCreator.getUpdatedCardResponse());

        var actual = updateService.execute(request);
        assertNotNull(actual);
        assertEquals(DTOCardCreator.getUpdatedCardResponse(), actual);
    }

    @Test
    public void testUpdateNotFoundException() {
        var request = new UpdateCardDTORequest(
                UUID.fromString("00009999-2222-1111-a456-426655440000"),
                new BigDecimal("5000"), "", null);

        when(repository.findById(request.getId())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> updateService.execute(request));
    }

    @Test
    public void testUpdateValidation() {
        var request = new UpdateCardDTORequest(
                null, new BigDecimal("5000"), "", null);

        var validator = Validation.buildDefaultValidatorFactory().getValidator();
        var set = validator.validate(request);
        var message = set.iterator().next();
        assertEquals("ID must not be null!", message.getMessage());
        assertEquals(1, set.size());
    }
}
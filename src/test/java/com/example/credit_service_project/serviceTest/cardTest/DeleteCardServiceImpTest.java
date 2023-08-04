package com.example.credit_service_project.serviceTest.cardTest;

import com.example.credit_service_project.repository.CardRepository;
import com.example.credit_service_project.service.errors.exceptions.NotFoundException;
import com.example.credit_service_project.serviceTest.generators.EntityCreator;
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
class DeleteCardServiceImpTest {

    @Mock
    CardRepository repository;
    @InjectMocks
    DeleteCardServiceImp delete;

    @Test
    public void testDeleteSuccess() {
        var request = new DeleteCardDTORequest(UUID.fromString("00009999-2222-1111-a456-426655440000"),
                "A10B3U3OI9");
        var card = EntityCreator.getCard();
        when(repository.findByIdAndCardNumber(request.getId(), request.getCardNumber())).thenReturn(Optional.of(card));

        var response = delete.execute(request);
        var expected = new DeleteCardDTOResponse(UUID.fromString("00009999-2222-1111-a456-426655440000"),
                "A10B3U3OI9", "A10B3U3OI9");
        assertNotNull(response);
        assertEquals(expected, response);
    }

    @Test
    public void testDeleteNotFoundException() {
        var request = new DeleteCardDTORequest(UUID.fromString("00009999-2222-1111-a456-426655440000"),
                "A10B3U3OI9");
        when(repository.findByIdAndCardNumber(request.getId(), request.getCardNumber())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> delete.execute(request));

    }

}
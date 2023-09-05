package com.example.credit_service_project.services.user;

import com.example.credit_service_project.DTO.user.UpdateClientRequest;
import com.example.credit_service_project.repositories.UserRepository;
import com.example.credit_service_project.services.generators.EntityCreator;
import com.example.credit_service_project.services.utils.UserUtil;
import com.example.credit_service_project.services.generators.DTOClientCreator;
import jakarta.validation.Validation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserUpdateServiceTest {
    @Mock
    private UserRepository repository;
    @Mock
    private UserUtil util;
    @InjectMocks
    private UserUpdateService service;

    @Test
    public void testAddClientSuccess() {
        var request = new UpdateClientRequest(UUID.randomUUID(), new BigDecimal("3500"), new BigDecimal("2000"));
        var client = EntityCreator.getClient();
        var updatedClient = EntityCreator.getUpdatedClient();

        when(repository.findById(request.getId())).thenReturn(Optional.of(client));
        when(util.updateClient(client, request)).thenReturn(updatedClient);
        when(util.convertClientToResponse(updatedClient)).thenReturn(DTOClientCreator.getUpdateResponse());

        assertEquals(DTOClientCreator.getUpdateResponse(), service.updateClient(request));
    }

    @Test
    public void testAddClientNotFoundException() {
        var request = new UpdateClientRequest(UUID.randomUUID(), new BigDecimal("3500"), new BigDecimal("2000"));
        when(repository.findById(request.getId())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.updateClient(request));
    }

    @Test
    public void testClientValidation() {
        var request = new UpdateClientRequest(null,
                new BigDecimal("0"), new BigDecimal("-2000"));
        var validation = Validation.buildDefaultValidatorFactory().getValidator();
        var set = validation.validate(request);
        assertEquals(3, set.size());
    }
}
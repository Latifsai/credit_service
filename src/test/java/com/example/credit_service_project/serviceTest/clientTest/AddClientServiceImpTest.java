package com.example.credit_service_project.serviceTest.clientTest;

import com.example.credit_service_project.DTO.client.AddClientRequest;
import com.example.credit_service_project.repositories.ClientRepository;
import com.example.credit_service_project.services.client.ClientCreateService;
import com.example.credit_service_project.services.manager.ManagerSearchService;
import com.example.credit_service_project.services.utils.ClientUtil;
import com.example.credit_service_project.serviceTest.generators.DTOClientCreator;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddClientServiceImpTest {
    @Mock
    private ClientRepository repository;
    @Mock
    private ManagerSearchService searchManagerService;
    @Mock
    private ClientUtil util;
    @InjectMocks
    private ClientCreateService service;

    @Test
    public void testAddClientSuccess() {
        var request = new AddClientRequest(UUID.randomUUID(), "Aziz", "Snow", new BigDecimal("2500"), new BigDecimal("1500"));
        var manager = EntityCreator.getManager();
        var client = EntityCreator.getClient();

        when(searchManagerService.findManagerById(request.getManagerID())).thenReturn(Optional.of(manager));
        when(util.convertAddRequestToEntity(request, manager)).thenReturn(client);
        when(util.convertClientToResponse(client)).thenReturn(DTOClientCreator.getResponse());

        assertEquals(DTOClientCreator.getResponse(), service.createClient(request));
    }

    @Test
    public void testAddClientNotFoundException() {
        var request = new AddClientRequest(UUID.randomUUID(), "Aziz", "Snow", new BigDecimal("2500"), new BigDecimal("1500"));
        when(searchManagerService.findManagerById(request.getManagerID())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.createClient(request));
    }

    @Test
    public void testAddClientValidation() {
        var request = new AddClientRequest(UUID.randomUUID(), "", null,
                new BigDecimal("0"), new BigDecimal("-13"));
        var validation = Validation.buildDefaultValidatorFactory().getValidator();
        var set = validation.validate(request);
        assertEquals(5, set.size());
    }

}
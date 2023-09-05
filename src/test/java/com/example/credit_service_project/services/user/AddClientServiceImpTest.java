package com.example.credit_service_project.services.user;

import com.example.credit_service_project.DTO.user.CreateUserRequest;
import com.example.credit_service_project.repositories.UserRepository;
import com.example.credit_service_project.services.generators.EntityCreator;
import com.example.credit_service_project.services.manager.ManagerSearchService;
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
class AddClientServiceImpTest {
    @Mock
    private UserRepository repository;
    @Mock
    private ManagerSearchService searchManagerService;
    @Mock
    private UserUtil util;
    @InjectMocks
    private UserCreateService service;

    @Test
    public void testAddClientSuccess() {
        var request = new CreateUserRequest(UUID.randomUUID(), "Aziz", "Snow", new BigDecimal("2500"), new BigDecimal("1500"));
        var manager = EntityCreator.getManager();
        var client = EntityCreator.getClient();

        when(searchManagerService.findManagerById(request.getManagerID())).thenReturn(Optional.of(manager));
        when(util.convertAddRequestToEntity(request, manager)).thenReturn(client);
        when(util.convertClientToResponse(client)).thenReturn(DTOClientCreator.getResponse());

        assertEquals(DTOClientCreator.getResponse(), service.createClient(request));
    }

    @Test
    public void testAddClientNotFoundException() {
        var request = new CreateUserRequest(UUID.randomUUID(), "Aziz", "Snow", new BigDecimal("2500"), new BigDecimal("1500"));
        when(searchManagerService.findManagerById(request.getManagerID())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.createClient(request));
    }

    @Test
    public void testAddClientValidation() {
        var request = new CreateUserRequest(UUID.randomUUID(), "", null,
                new BigDecimal("0"), new BigDecimal("-13"));
        var validation = Validation.buildDefaultValidatorFactory().getValidator();
        var set = validation.validate(request);
        assertEquals(5, set.size());
    }

}
package com.example.credit_service_project.services.account;

import com.example.credit_service_project.repositories.AccountRepository;
import com.example.credit_service_project.services.client.ClientSearchService;
import com.example.credit_service_project.services.generators.DTOAccountCreator;
import com.example.credit_service_project.services.generators.EntityCreator;
import com.example.credit_service_project.services.utils.AccountUtil;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateAccountImpTest {

    @Mock
    private AccountRepository repository;
    @Mock
    private ClientSearchService searchClientService;
    @Mock
    private AccountUtil util;
    @InjectMocks
    private AccountCreationService service;

    @Test
    @DisplayName("Test when input is correct")
    public void testCreateAccountImp() {
        var request = DTOAccountCreator.createRequest();
        var account = EntityCreator.getAccount();
        var client = EntityCreator.getClient();

        when(searchClientService.findClientById(request.getClientId())).thenReturn(Optional.of(client));

        when(util.convertAddRequestToAccount(request, client)).thenReturn(account);

        when(repository.save(account)).thenReturn(account);

        when(util.convertAccountToAddResponse(account)).thenReturn(DTOAccountCreator.createDTOResponse());

        var actual = service.createAccount(DTOAccountCreator.createRequest());
        var expected = DTOAccountCreator.createDTOResponse();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test when input contains null")
    public void testCreateAccountImpWithException() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        var account = DTOAccountCreator.getRequestWithExceptions();
        var violations = validator.validate(account);

        assertEquals(4, violations.size());
    }
}

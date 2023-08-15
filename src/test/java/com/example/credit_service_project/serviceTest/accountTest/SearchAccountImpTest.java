package com.example.credit_service_project.serviceTest.accountTest;

import com.example.credit_service_project.DTO.accountDTO.SearchAccountRequest;
import com.example.credit_service_project.repository.AccountRepository;
import com.example.credit_service_project.service.account.SearchAccountsServiceImp;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.service.utils.AccountUtil;
import com.example.credit_service_project.serviceTest.generators.DTOAccountCreator;
import com.example.credit_service_project.serviceTest.generators.EntityCreator;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchAccountImpTest {

    @Mock
    private AccountRepository repository;
    @Mock
    private AccountUtil util;

    @InjectMocks
    private SearchAccountsServiceImp service;

    @Test
    public void testSearchIfPresent() {
        var request = new SearchAccountRequest(UUID.randomUUID(), "A10B3U3OI9");
        var account = EntityCreator.getAccount();
        when(repository.findByIdOrAccountNumber(request.getId(), request.getAccountNumber()))
                .thenReturn(Optional.of(account));
        when(util.convertAccountToAddResponse(account)).thenReturn(DTOAccountCreator.createDTOResponse());

        assertEquals(DTOAccountCreator.createDTOResponse(), service.execute(request));
    }

    @Test
    public void testSearchIfNotPresent() {
        var request = new SearchAccountRequest(UUID.fromString("9-2222-1111-a456-426655440000"),
                "A1OI9");

        when(repository.findByIdOrAccountNumber(request.getId(), request.getAccountNumber()))
                .thenThrow(new NotFoundException(ErrorsMessage.NOT_FOUND_ACCOUNT_MESSAGE));

        assertThrows(NotFoundException.class, () -> service.execute(request));
    }

    @Test
    public void testSearchRequestValidation() {
        var request = new SearchAccountRequest(null,
                "");
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        var set = validator.validate(request);
        assertEquals(2, set.size());
    }

}

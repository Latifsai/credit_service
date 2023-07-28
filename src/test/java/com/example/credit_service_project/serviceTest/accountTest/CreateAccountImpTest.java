package com.example.credit_service_project.serviceTest.accountTest;

import com.example.credit_service_project.DTO.accountDTO.AddAccountDTORequest;
import com.example.credit_service_project.DTO.accountDTO.AddAccountDTOResponse;
import com.example.credit_service_project.service.AccountService;
import com.example.credit_service_project.serviceTest.generators.DTOCreator;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateAccountImpTest {

    @Mock
    private AccountService<AddAccountDTOResponse, AddAccountDTORequest> service;

    private final Validator validator
            = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    @DisplayName("Test when input is correct")
    public void testCreateAccountImp() {
         when(service.execute(DTOCreator.createRequest())).thenReturn(DTOCreator.createAddResponse());

        var actual = service.execute(DTOCreator.createRequest());
        var expected = DTOCreator.createAddResponse();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test when input contains null")
    public void testCreateAccountImpWithException() {
        var request = DTOCreator.createRequestWithExceptions();
        var violations = validator.validate(request);

        assertEquals(5, violations.size());
    }
}

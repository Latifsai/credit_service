package com.example.credit_service_project.serviceTest.accountTest;

import com.example.credit_service_project.DTO.accountDTO.UpdateAccountRequest;
import com.example.credit_service_project.DTO.accountDTO.UpdateAccountResponse;
import com.example.credit_service_project.service.AccountService;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.NotFoundException;
import com.example.credit_service_project.serviceTest.generators.DTOAccountCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateServiceImpTest {

    @Mock
    private AccountService<UpdateAccountResponse, UpdateAccountRequest> service;

    @Test
    public void upgradeTestIfExist() {
        var request = DTOAccountCreator.getUpdateRequest();
        when(service.execute(request)).thenReturn(DTOAccountCreator.getUpdateResponse());

        var expected = DTOAccountCreator.getUpdateResponse();
        var actual = service.execute(request);
        assertEquals(expected, actual);
    }

    @Test
    public void upgradeTestNotFound() {
        var request = DTOAccountCreator.getUpdateRequest();
        when(service.execute(request)).thenThrow(new NotFoundException(ErrorsMessage.NOT_FOUND_ACCOUNT_MESSAGE));

        assertThrows(NotFoundException.class, () -> {
            service.execute(request);
        });
    }
}

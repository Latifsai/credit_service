package com.example.credit_service_project.serviceTest.accountTest;

import com.example.credit_service_project.DTO.accountDTO.UpdateAccountRequest;
import com.example.credit_service_project.DTO.accountDTO.UpdateAccountResponse;
import com.example.credit_service_project.service.AccountService;
import com.example.credit_service_project.service.exeption.ErrorsMessage;
import com.example.credit_service_project.service.exeption.NotFoundException;
import com.example.credit_service_project.serviceTest.generators.DTOCreator;
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
        var request = DTOCreator.getUpdateRequest();
        when(service.execute(request)).thenReturn(DTOCreator.getUpdateResponse());

        var expected = DTOCreator.getUpdateResponse();
        var actual = service.execute(request);
        assertEquals(expected, actual);
    }

    @Test
    public void upgradeTestNotFound() {
        var request = DTOCreator.getUpdateRequest();
        when(service.execute(request)).thenThrow(new NotFoundException(ErrorsMessage.NOT_FOUND_ACCOUNT_MESSAGE));

        assertThrows(NotFoundException.class, () -> {
            service.execute(request);
        });
    }
}

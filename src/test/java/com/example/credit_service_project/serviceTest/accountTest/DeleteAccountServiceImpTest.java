package com.example.credit_service_project.serviceTest.accountTest;

import com.example.credit_service_project.DTO.accountDTO.DeleteAccountResponse;
import com.example.credit_service_project.DTO.accountDTO.SearchAndDeleteAccountRequest;
import com.example.credit_service_project.entity.enums.AccountStatus;
import com.example.credit_service_project.service.AccountService;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteAccountServiceImpTest {

    @Mock
    private AccountService<DeleteAccountResponse, SearchAndDeleteAccountRequest> service;
    @Test
    public void testDeleteIfAccountExist() {
        var request = new SearchAndDeleteAccountRequest(UUID.fromString("00009999-2222-1111-a456-426655440000"), "A10B3U3OI9");
        when(service.execute(request)).thenReturn(new DeleteAccountResponse(UUID.fromString("00009999-2222-1111-a456-426655440000"),
                "A10B3U3OI9", AccountStatus.ACTIVE, new BigDecimal("3000")));

        var expected = new DeleteAccountResponse(UUID.fromString("00009999-2222-1111-a456-426655440000"),
                "A10B3U3OI9", AccountStatus.ACTIVE, new BigDecimal("3000"));
        var actual = service.execute(request);
        assertEquals(expected, actual);
    }
    @Test
    public void testDeleteIfNotFount() {
        var request = new SearchAndDeleteAccountRequest(UUID.fromString("00009999-3332-6776-a446-426655440000"), "A19");
        when(service.execute(request)).thenThrow(new NotFoundException(ErrorsMessage.NOT_FOUND_ACCOUNT_MESSAGE));
        assertThrows(NotFoundException.class, () -> {
            service.execute(request);
        });
    }

}

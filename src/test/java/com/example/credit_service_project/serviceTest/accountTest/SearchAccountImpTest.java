package com.example.credit_service_project.serviceTest.accountTest;

import com.example.credit_service_project.DTO.accountDTO.SearchAccountResponse;
import com.example.credit_service_project.DTO.accountDTO.SearchAndDeleteAccountRequest;
import com.example.credit_service_project.service.AccountService;
import com.example.credit_service_project.service.exeption.ErrorsMessage;
import com.example.credit_service_project.service.exeption.NotFoundException;
import com.example.credit_service_project.serviceTest.generators.DTOCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchAccountImpTest {
    @Mock
    private AccountService<SearchAccountResponse, SearchAndDeleteAccountRequest> service;

    @Test
    public void testSearchIfPresent() {
        var request = new SearchAndDeleteAccountRequest(UUID.fromString("00009999-2222-1111-a456-426655440000"),
                "A10B3U3OI9");
        when(service.execute(request)).thenReturn(DTOCreator.getSearchResponse());
        var expected = DTOCreator.getSearchResponse();
        var actual = service.execute(request);

        assertEquals(expected, actual);
    }

    @Test
    public void testSearchIfNotPresent() {
        var request = new SearchAndDeleteAccountRequest(UUID.fromString("9-2222-1111-a456-426655440000"),
                "A1OI9");
        when(service.execute(request)).thenThrow(new NotFoundException(ErrorsMessage.NOT_FOUND_ACCOUNT_MESSAGE));

        assertThrows(NotFoundException.class, () -> {
            service.execute(request);
        });
    }
}

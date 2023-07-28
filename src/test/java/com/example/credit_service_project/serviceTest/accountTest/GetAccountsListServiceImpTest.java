package com.example.credit_service_project.serviceTest.accountTest;

import com.example.credit_service_project.DTO.accountDTO.GetAccountsListRequest;
import com.example.credit_service_project.DTO.accountDTO.GetAccountsListResponse;
import com.example.credit_service_project.entity.enums.AccountStatus;
import com.example.credit_service_project.service.AccountService;
import com.example.credit_service_project.serviceTest.generators.EntityCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAccountsListServiceImpTest {

    @Mock
    AccountService<GetAccountsListResponse, GetAccountsListRequest> service;
    @Test
    public void getAccountsListTest() {
        var accounts = List.of(EntityCreator.getAccount());
        var request = new GetAccountsListRequest(AccountStatus.ACTIVE);
        when(service.execute(request))
                .thenReturn(new GetAccountsListResponse(accounts));

        var expected = new GetAccountsListResponse(accounts);
        var actual = service.execute(request);
        assertEquals(expected, actual);
    }
}

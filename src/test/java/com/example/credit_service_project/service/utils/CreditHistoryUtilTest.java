package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.dto.CreditHistoryResponse;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.CreditHistory;
import com.example.credit_service_project.generators.CreditHistoryDTOGenerator;
import com.example.credit_service_project.generators.EntityCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreditHistoryUtilTest {

    @Mock
    private CreditHistoryUtil util;
    private final Account account = EntityCreator.getAccount();
    private final CreditHistory creditHistory = EntityCreator.getCreditHistory();
    private final CreditHistoryResponse response = CreditHistoryDTOGenerator.getResponse();

    @Test
    void createHistoryFromAccount() {
        when(util.createHistoryFromAccount(account)).thenReturn(creditHistory);
        assertEquals(creditHistory, util.createHistoryFromAccount(account));
    }

    @Test
    void convertEntityToResponse() {
        when(util.convertEntityToResponse(creditHistory)).thenReturn(response);
        assertEquals(response, util.convertEntityToResponse(creditHistory));
    }
}
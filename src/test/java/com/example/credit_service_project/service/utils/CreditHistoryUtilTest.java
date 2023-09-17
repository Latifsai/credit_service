package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.CreditHistory;
import com.example.credit_service_project.generators.EntityCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CreditHistoryUtilTest {

    @InjectMocks
    private CreditHistoryUtil util;
    private final Account account = EntityCreator.getAccount();
    private final CreditHistory creditHistory = EntityCreator.getCreditHistory();

    @Test
    @DisplayName("Test createHistoryFromAccount method")
    void createHistoryFromAccount() {
        assertNotNull(util.createHistoryFromAccount(account));
    }

    @Test
    @DisplayName("Test convertEntityToResponse method")
    void convertEntityToResponse() {
        creditHistory.setDelays(Collections.singletonList(EntityCreator.getDelay()));
        creditHistory.setAccount(account);
        assertNotNull(util.convertEntityToResponse(creditHistory));
    }
}
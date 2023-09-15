package com.example.credit_service_project.service.delay;

import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Delay;
import com.example.credit_service_project.generators.DelayDTOGenerator;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.repository.DelayRepository;
import com.example.credit_service_project.service.creditHistory.CreditHistoryService;
import com.example.credit_service_project.service.utils.DelayUtil;
import org.hibernate.sql.ast.tree.expression.Collation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DelayServiceTest {
    @Mock
    private CreditHistoryService service;
    @Mock
    private DelayRepository repository;
    @Mock
    private DelayUtil util;
    @InjectMocks
    private DelayService delayService;

    private final Account account = EntityCreator.getAccount();
    private final Delay delay = EntityCreator.getDelay();
    @Test
    void addNewDelay() {
        var response = DelayDTOGenerator.getResponse();

        when(util.createEntity(BigDecimal.TEN)).thenReturn(delay);
        when(repository.save(delay)).thenReturn(delay);
        when(util.convertToResponse(delay)).thenReturn(response);

        var actual = delayService.addNewDelay(BigDecimal.TEN, account);

        assertEquals(response, actual);
        verify(repository, times(1)).save(delay);
        verify(util, times(1)).convertToResponse(delay);
    }

    @Test
    void findAllDelaysBelongsToCreditHistory() {
        var history = EntityCreator.getCreditHistory();
        var delays = Collections.singletonList(delay);

        when(service.findByIDForService(UUID.fromString("ee915b27-a4d5-4d69-b395-5755a01a6b24"))).thenReturn(history);
        when(repository.findAllByCreditHistory(history)).thenReturn(delays);
        when(util.convertToResponse(delay)).thenReturn(DelayDTOGenerator.getResponse());

        var actual = delayService.findAllDelaysBelongsToCreditHistory(UUID.fromString("ee915b27-a4d5-4d69-b395-5755a01a6b24"));

        assertEquals(1, actual.size());
    }
}
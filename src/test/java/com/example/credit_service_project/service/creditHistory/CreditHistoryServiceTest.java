package com.example.credit_service_project.service.creditHistory;

import com.example.credit_service_project.dto.CreditHistoryResponse;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.CreditHistory;
import com.example.credit_service_project.generators.CreditHistoryDTOGenerator;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.repository.CreditHistoryRepository;
import com.example.credit_service_project.service.utils.CreditHistoryUtil;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreditHistoryServiceTest {

    @Mock
    private CreditHistoryRepository repository;
    @Mock
    private CreditHistoryUtil util;
    @InjectMocks
    private CreditHistoryService historyService;

    private final CreditHistory history = EntityCreator.getCreditHistory();
    private final CreditHistoryResponse response = CreditHistoryDTOGenerator.getResponse();
    private final UUID id = UUID.fromString("6e6cdb58-2178-42da-86c4-10f8c2ee36c2");

    @Test
    void createHistory() {
        Account account = EntityCreator.getAccount();

        when(util.createHistoryFromAccount(account)).thenReturn(history);
        when(repository.save(history)).thenReturn(history);
        when(util.convertEntityToResponse(history)).thenReturn(response);

        var actual = historyService.createHistory(account);

        assertEquals(response, actual);
        verify(repository, times(1)).save(history);
    }

    @Test
    void findByID() {

        when(repository.findById(id)).thenReturn(Optional.of(history));
        when(util.convertEntityToResponse(history)).thenReturn(response);

        assertEquals(response, historyService.findByID(id));
        verify(util, times(1)).convertEntityToResponse(history);
    }

    @Test
    void findByIDForService() {

        when(repository.findById(id)).thenReturn(Optional.of(history));
        assertEquals(history, historyService.findByIDForService(id));
    }


    @Test
    void findByIDForServiceNotFound() {

        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> historyService.findByIDForService(id));
    }

    @Test
    void findAllHistories() {
        var responses = Collections.singletonList(response);
        var list = Collections.singletonList(history);
        var result = CreditHistoryDTOGenerator.getResponse();

        when(repository.findAll()).thenReturn(list);
        when(util.convertEntityToResponse(history)).thenReturn(result);

        var actual = historyService.findAllHistories();

        assertEquals(Collections.singletonList(result), actual);
        verify(repository, times(1)).findAll();
    }
}
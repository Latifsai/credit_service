package com.example.credit_service_project.service.creditOrder;

import com.example.credit_service_project.dto.creditOrderDTO.CreditOrderResponseDTO;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.repository.CreditOrderRepository;
import com.example.credit_service_project.generators.CreditOrderedGenerator;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.service.utils.CreditOrderUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DecisionOrderServiceTest {

    @Mock
    private GetAllCreditOrdersService getAllCreditService;
    @Mock
    private CreditOrderRepository repository;
    @Mock
    private CreditOrderUtil util;
    @InjectMocks
    private DecisionOrderService decisionOrderService;

    @Test
    @DisplayName("Test accept order method")
    void acceptOrder() {
        CreditOrder order = EntityCreator.getCreditOrder();
        List<CreditOrder> orders = List.of(order);
        List<CreditOrderResponseDTO> responsesList = List.of(CreditOrderedGenerator.getResponse());

        when(getAllCreditService.getOrdersIn_Review()).thenReturn(orders);
        when(util.considerationOfApplication(order)).thenReturn(order);
        when(repository.save(order)).thenReturn(order);
        when(util.convertToResponse(order)).thenReturn(responsesList.get(0));

        List<CreditOrderResponseDTO> result = decisionOrderService.acceptOrder();

        assertEquals(1, result.size());
        verify(getAllCreditService, times(1)).getOrdersIn_Review();
        verify(util, times(1)).considerationOfApplication(order);
        verify(repository, times(1)).save(order);
        verify(util, times(1)).convertToResponse(order);
    }

    @Test
    @DisplayName("Test accept order method throws NotAllowed")
    void acceptOrderNotAllowed() {
        CreditOrder order = EntityCreator.getCreditOrderNotAllowed();
        List<CreditOrder> orders = List.of(order);

        when(getAllCreditService.getOrdersIn_Review()).thenReturn(orders);

        List<CreditOrderResponseDTO> result = decisionOrderService.acceptOrder();

        assertFalse(result.isEmpty());
        verify(getAllCreditService, times(1)).getOrdersIn_Review();
    }

}
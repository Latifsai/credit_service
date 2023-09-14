package com.example.credit_service_project.services.creditOrder;

import com.example.credit_service_project.dto.creditOrderDTO.CreditOrderResponseDTO;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.repositories.CreditOrderRepository;
import com.example.credit_service_project.services.generators.CreditOrderedGenerator;
import com.example.credit_service_project.services.generators.EntityCreator;
import com.example.credit_service_project.services.utils.CreditOrderUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void acceptOrder() {
        CreditOrder order = EntityCreator.getCreditOrder();
        List<CreditOrder> orders = List.of(order);
        List<CreditOrderResponseDTO> responsesList = List.of(CreditOrderedGenerator.getResponse());

        when(getAllCreditService.getOrders()).thenReturn(orders);
        when(util.considerationOfApplication(order)).thenReturn(order);
        when(repository.save(order)).thenReturn(order);
        when(util.convertToResponse(order)).thenReturn(responsesList.get(0));

        List<CreditOrderResponseDTO> result = decisionOrderService.acceptOrder();

        assertEquals(responsesList.size(), result.size());
        verify(repository, times(1)).save(order);
    }

    @Test
    void acceptOrderNotAllowed() {
        CreditOrder order = EntityCreator.getCreditOrderNotAllowed();
        List<CreditOrder> orders = List.of(order);

        when(getAllCreditService.getOrders()).thenReturn(orders);

        List<CreditOrderResponseDTO> result = decisionOrderService.acceptOrder();

        assertEquals(Collections.emptyList(), result);
        verify(repository, times(0)).save(order);
    }

}
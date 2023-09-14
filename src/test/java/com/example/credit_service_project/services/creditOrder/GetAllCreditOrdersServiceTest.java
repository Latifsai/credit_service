package com.example.credit_service_project.services.creditOrder;

import com.example.credit_service_project.dto.creditOrderDTO.CreditOrderResponseDTO;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.repositories.CreditOrderRepository;
import com.example.credit_service_project.generators.CreditOrderedGenerator;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.services.utils.CreditOrderUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllCreditOrdersServiceTest {
    @Mock
    private CreditOrderRepository repository;
    @Mock
    private CreditOrderUtil util;
    @InjectMocks
    private GetAllCreditOrdersService getAllCreditOrdersService;

    @Test
    void getAllCreditOrders() {
        CreditOrder order = EntityCreator.getCreditOrder();
        List<CreditOrder> creditOrders = List.of(order);

        CreditOrderResponseDTO dto = CreditOrderedGenerator.getResponse();
        List<CreditOrderResponseDTO> list = List.of(dto);

        when(repository.findAll()).thenReturn(creditOrders);
        when(util.convertToResponse(order)).thenReturn(dto);

        List<CreditOrderResponseDTO> result = getAllCreditOrdersService.getAllCreditOrders();

        assertEquals(list, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    void getOrders() {
        CreditOrder order = EntityCreator.getCreditOrder();
        List<CreditOrder> creditOrders = List.of(order);

        when(repository.findAll()).thenReturn(creditOrders);

        List<CreditOrder> result = getAllCreditOrdersService.getOrders();
        assertEquals(creditOrders, result);
        verify(repository, times(1)).findAll();
    }
}
package com.example.credit_service_project.service.creditOrder;

import com.example.credit_service_project.dto.creditOrderDTO.CreditOrderResponseDTO;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.repository.CreditOrderRepository;
import com.example.credit_service_project.generators.CreditOrderedGenerator;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.service.utils.CreditOrderUtil;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreditOrderSearchServiceTest {

    @Mock
    private  CreditOrderRepository repository;
    @Mock
    private  CreditOrderUtil util;
    @InjectMocks
    private CreditOrderSearchService creditOrderSearchService;

    private final UUID id = UUID.randomUUID();
    private final CreditOrder order = EntityCreator.getCreditOrder();

    @Test
    @DisplayName("Test search credit order method")
    void searchCreditOrder() {
        CreditOrderResponseDTO dto = CreditOrderedGenerator.getResponse();

        when(repository.findById(id)).thenReturn(Optional.of(order));
        when(util.convertToResponse(order)).thenReturn(dto);

        CreditOrderResponseDTO result = creditOrderSearchService.searchCreditOrder(id);

        assertEquals(dto, result);
        verify(repository, times(1)).findById(id);
        verify(util, times(1)).convertToResponse(order);
    }

    @Test
    @DisplayName("Test find by ID method")
    void findById() {
        when(repository.findById(id)).thenReturn(Optional.of(order));
        CreditOrder result = creditOrderSearchService.findById(id);

        assertEquals(order, result);
        verify(repository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Test find by ID method throws NotFoundException")
    void findByIdNotFoundException() {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> creditOrderSearchService.searchCreditOrder(id));
        verify(repository, times(1)).findById(id);
    }

}
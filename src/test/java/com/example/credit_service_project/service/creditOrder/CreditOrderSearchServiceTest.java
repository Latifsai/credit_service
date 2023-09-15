package com.example.credit_service_project.service.creditOrder;

import com.example.credit_service_project.dto.creditOrderDTO.CreditOrderResponseDTO;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.repository.CreditOrderRepository;
import com.example.credit_service_project.generators.CreditOrderedGenerator;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.service.utils.CreditOrderUtil;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
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
    @Test
    void searchCreditOrder() {
        UUID id = UUID.randomUUID();
        CreditOrder order = EntityCreator.getCreditOrder();
        CreditOrderResponseDTO dto = CreditOrderedGenerator.getResponse();

        when(repository.findById(id)).thenReturn(Optional.of(order));
        when(util.convertToResponse(order)).thenReturn(dto);

        CreditOrderResponseDTO result = creditOrderSearchService.searchCreditOrder(id);

        assertEquals(dto, result);
        verify(repository, times(1)).findById(id);
    }

    @Test
    void findById() {
        UUID id = UUID.randomUUID();
        CreditOrder order = EntityCreator.getCreditOrder();

        when(repository.findById(id)).thenReturn(Optional.of(order));
        CreditOrder result = creditOrderSearchService.findById(id);

        assertEquals(order, result);
        verify(repository, times(1)).findById(id);
    }

    @Test
    void findByIdNotFoundException() {
        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> creditOrderSearchService.searchCreditOrder(id));
        verify(repository, times(1)).findById(id);
    }

}
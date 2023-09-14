package com.example.credit_service_project.services.creditOrder;

import com.example.credit_service_project.dto.creditOrderDTO.CreditOrderResponseDTO;
import com.example.credit_service_project.dto.creditOrderDTO.UpdateCreditOrderRequestDTO;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.entity.enums.CreditOrderStatus;
import com.example.credit_service_project.services.generators.CreditOrderedGenerator;
import com.example.credit_service_project.services.generators.EntityCreator;
import com.example.credit_service_project.services.utils.CreditOrderUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreditOrderUpdateServiceTest {

    @Mock
    private CreditOrderCreateService addCreditOrderService;
    @Mock
    private CreditOrderSearchService searchCreditOrderService;
    @Mock
    private CreditOrderUtil util;
    @InjectMocks
    private CreditOrderUpdateService creditOrderUpdateService;
    @Test
    void updateCreditOrder() {
        UpdateCreditOrderRequestDTO request = new UpdateCreditOrderRequestDTO(UUID.fromString("c3009377-3b57-4965-8540-69d56fce34f4"), null,
                null, null, CreditOrderStatus.CLOSED);

        CreditOrder order = EntityCreator.getCreditOrder();
        CreditOrder updatedOrder = EntityCreator.getUpdatedCreditOrder();
        CreditOrderResponseDTO dto = CreditOrderedGenerator.getResponse();

        when(searchCreditOrderService.findById(request.getId())).thenReturn(order);
        when(util.update(request, order)).thenReturn(updatedOrder);
        when(addCreditOrderService.saveOrder(updatedOrder)).thenReturn(updatedOrder);
        when(util.convertToResponse(updatedOrder)).thenReturn(dto);

        CreditOrderResponseDTO result = creditOrderUpdateService.updateCreditOrder(request);

        Assertions.assertEquals(dto, result);
        verify(searchCreditOrderService, times(1)).findById(result.getId());
        verify(addCreditOrderService, times(1)).saveOrder(updatedOrder);
    }
}
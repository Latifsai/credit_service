package com.example.credit_service_project.services.utils;

import com.example.credit_service_project.dto.creditOrderDTO.CreateCreditOrderRequestDTO;
import com.example.credit_service_project.dto.creditOrderDTO.CreateCreditOrderResponseDTO;
import com.example.credit_service_project.dto.creditOrderDTO.CreditOrderResponseDTO;
import com.example.credit_service_project.dto.creditOrderDTO.UpdateCreditOrderRequestDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.services.generators.CreditOrderedGenerator;
import com.example.credit_service_project.services.generators.EntityCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreditOrderUtilTest {

    @Mock
    private CreditOrderUtil util;

    private CreditOrder creditOrder;
    private CreditOrderResponseDTO response;

    @BeforeEach
    public void init() {
        creditOrder = EntityCreator.getCreditOrder();
        response = CreditOrderedGenerator.getResponse();
    }

    @Test
    void convertAddRequestToEntity() {
        CreateCreditOrderRequestDTO request = new CreateCreditOrderRequestDTO(1L, UUID.randomUUID(), null, 10);

        Product product = EntityCreator.getProduct();
        Account account = EntityCreator.getAccount();

        when(util.convertAddRequestToEntity(request, product, account)).thenReturn(creditOrder);
        assertEquals(creditOrder, util.convertAddRequestToEntity(request, product, account));
    }

    @Test
    void convertToAddResponse() {
        CreateCreditOrderResponseDTO response = CreditOrderedGenerator.getCreateResponse();
        Product product = EntityCreator.getProduct();
        Account account = EntityCreator.getAccount();

        when(util.convertToAddResponse(creditOrder, product, account)).thenReturn(response);
        assertEquals(response, util.convertToAddResponse(creditOrder, product, account));
    }

    @Test
    void convertToResponse() {
        when(util.convertToResponse(creditOrder)).thenReturn(response);
        assertEquals(response, util.convertToResponse(creditOrder));
    }

    @Test
    void update() {
        UpdateCreditOrderRequestDTO request = new UpdateCreditOrderRequestDTO(UUID.randomUUID(), null, null, null, null);
        CreditOrder updated = EntityCreator.getUpdatedCreditOrder();

        when(util.update(request, creditOrder)).thenReturn(updated);
        CreditOrder result = util.update(request, updated);

        assertEquals(updated, result);

    }


    @Test
    void considerationOfApplication() {
        CreditOrder updated = EntityCreator.getUpdatedCreditOrder();

        when(util.considerationOfApplication(creditOrder)).thenReturn(updated);
        assertEquals(updated, util.considerationOfApplication(creditOrder));
    }
}
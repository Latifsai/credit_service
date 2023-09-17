package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.dto.creditOrderDTO.CreateCreditOrderRequestDTO;
import com.example.credit_service_project.dto.creditOrderDTO.UpdateCreditOrderRequestDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.generators.EntityCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CreditOrderUtilTest {

    @InjectMocks
    private CreditOrderUtil util;

    private CreditOrder creditOrder;
    private Product product;
    private Account account;

    @BeforeEach
    public void init() {
        creditOrder = EntityCreator.getCreditOrder();
        product = EntityCreator.getProduct();
        account = EntityCreator.getAccount();
    }

    @Test
    @DisplayName("Test convertAddRequestToEntity method")
    void convertAddRequestToEntity() {
        CreateCreditOrderRequestDTO request = new CreateCreditOrderRequestDTO(1L, UUID.randomUUID(), null, 10);
        assertNotNull(util.convertAddRequestToEntity(request, product, account));
    }

    @Test
    @DisplayName("Test convertToAddResponse method")
    void convertToAddResponse() {
        Product product = EntityCreator.getProduct();
        Account account = EntityCreator.getAccount();

        assertNotNull(util.convertToAddResponse(creditOrder, product, account));
    }

    @Test
    @DisplayName("Test convertToResponse method")
    void convertToResponse() {
        assertNotNull(util.convertToResponse(creditOrder));
    }

    @Test
    @DisplayName("Test update method")
    void update() {
        UpdateCreditOrderRequestDTO request = new UpdateCreditOrderRequestDTO(UUID.randomUUID(), null, null, null, null);
        CreditOrder updated = EntityCreator.getUpdatedCreditOrder();

        CreditOrder result = util.update(request, updated);

        assertEquals(updated, result);

    }


    @Test
    @DisplayName("Test considerationOfApplication method")
    void considerationOfApplication() {
        CreditOrder updated = EntityCreator.getUpdatedCreditOrder();

        assertEquals(updated, util.considerationOfApplication(creditOrder));
    }
}
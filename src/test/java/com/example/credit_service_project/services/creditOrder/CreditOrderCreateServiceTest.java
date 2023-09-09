package com.example.credit_service_project.services.creditOrder;

import com.example.credit_service_project.DTO.creditOrderDTO.CreateCreditOrderDTORequest;
import com.example.credit_service_project.DTO.creditOrderDTO.CreateCreditOrderResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.repositories.CreditOrderRepository;
import com.example.credit_service_project.services.account.AccountSearchService;
import com.example.credit_service_project.services.generators.CreditOrderedGenerator;
import com.example.credit_service_project.services.generators.EntityCreator;
import com.example.credit_service_project.services.product.ProductSearchService;
import com.example.credit_service_project.services.utils.CreditOrderUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreditOrderCreateServiceTest {

    @Mock
    private CreditOrderRepository repository;
    @Mock
    private ProductSearchService searchProductService;
    @Mock
    private AccountSearchService accountSearchService;
    @Mock
    private CreditOrderUtil util;
    @InjectMocks
    private CreditOrderCreateService createService;

    @Test
    void createCreditOrder() {
        CreateCreditOrderDTORequest request = new CreateCreditOrderDTORequest(1L, UUID.randomUUID(), null, 10);
        Product product = EntityCreator.getProduct();
        Account account = EntityCreator.getAccount();
        CreditOrder order = EntityCreator.getCreditOrder();

        when(searchProductService.findById(request.getProductID())).thenReturn(product);
        when(accountSearchService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber())).thenReturn(account);
        when(util.convertAddRequestToEntity(request, product, account)).thenReturn(order);
        when(repository.save(order)).thenReturn(order);
        when(util.convertToAddResponse(order, product, account)).thenReturn(CreditOrderedGenerator.getCreateResponse());

        CreateCreditOrderResponseDTO result = createService.createCreditOrder(request);

        assertNotNull(result);
        verify(searchProductService, times(1)).findById(request.getProductID());
        verify(accountSearchService, times(1)).findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber());
        verify(repository, times(1)).save(order);
    }

    @Test
    void saveOrder() {
        CreditOrder order = EntityCreator.getCreditOrder();

        when(repository.save(order)).thenReturn(order);
        CreditOrder result = createService.saveOrder(order);

        assertEquals(order, result);
        verify(repository, times(1)).save(order);
    }
}
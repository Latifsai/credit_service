package com.example.credit_service_project.services.creditOrder;

import com.example.credit_service_project.DTO.creditOrderDTO.CreateCreditOrderRequestDTO;
import com.example.credit_service_project.DTO.creditOrderDTO.CreateCreditOrderResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.repositories.CreditOrderRepository;
import com.example.credit_service_project.services.account.AccountSearchService;
import com.example.credit_service_project.services.product.ProductSearchService;
import com.example.credit_service_project.services.utils.CreditOrderUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreditOrderCreateService {

    private final CreditOrderRepository repository;
    private final ProductSearchService searchProductService;
    private final AccountSearchService accountSearchService;
    private final CreditOrderUtil util;

    public CreateCreditOrderResponseDTO createCreditOrder(CreateCreditOrderRequestDTO request) {
        Product product = searchProductService.findById(request.getProductID());
        Account account = accountSearchService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber());

        CreditOrder creditOrder = util.convertAddRequestToEntity(request, product, account);
        CreditOrder savedOrder = saveOrder(creditOrder);
        log.info("Create credit order with number: {}", savedOrder.getNumber());
        return util.convertToAddResponse(savedOrder, product, account);
    }

    public CreditOrder saveOrder(CreditOrder creditOrder) {
        return repository.save(creditOrder);
    }
}

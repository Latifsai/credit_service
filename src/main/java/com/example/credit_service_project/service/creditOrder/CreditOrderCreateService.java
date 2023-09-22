package com.example.credit_service_project.service.creditOrder;

import com.example.credit_service_project.dto.creditOrderDTO.CreateCreditOrderRequestDTO;
import com.example.credit_service_project.dto.creditOrderDTO.CreateCreditOrderResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.repository.CreditOrderRepository;
import com.example.credit_service_project.service.account.AccountSearchService;
import com.example.credit_service_project.service.product.ProductSearchService;
import com.example.credit_service_project.service.utils.CreditOrderUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class CreditOrderCreateService {

    private final CreditOrderRepository repository;
    private final ProductSearchService searchProductService;
    private final AccountSearchService accountSearchService;
    private final CreditOrderUtil util;

    /**
     * Here upon request will be created Order
     * @param request CreateCreditOrderRequestDTO
     * @return CreateCreditOrderResponseDTO
     */
    public CreateCreditOrderResponseDTO createCreditOrder(CreateCreditOrderRequestDTO request) {
        Product product = searchProductService.findById(request.getProductID());
        Account account = accountSearchService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber());

        CreditOrder creditOrder = util.convertAddRequestToEntity(request, product, account);
        CreditOrder savedOrder = saveOrder(creditOrder);
        log.info("Create credit order with number: {}", savedOrder.getNumber());
        return util.convertToAddResponse(savedOrder, product, account);
    }

    /**
     * Save CreditOrder in DB
     * @param creditOrder CreditOrder
     * @return CreditOrder
     */
    public CreditOrder saveOrder(CreditOrder creditOrder) {
        return repository.save(creditOrder);
    }
}

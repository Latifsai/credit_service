package com.example.credit_service_project.services.creditOrder;

import com.example.credit_service_project.DTO.creditOrderDTO.CreateCreditOrderDTORequest;
import com.example.credit_service_project.DTO.creditOrderDTO.CreateCreditOrderResponseDTO;
import com.example.credit_service_project.entity.Client;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.repositories.CreditOrderRepository;
import com.example.credit_service_project.services.client.ClientSearchService;
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
    private final ClientSearchService searchClientService;
    private final CreditOrderUtil util;

    public CreateCreditOrderResponseDTO createCreditOrder(CreateCreditOrderDTORequest request) {
        Product product = searchProductService.findById(request.getProductID());
        Client client = searchClientService.findClientById(request.getClientID());

        CreditOrder creditOrder = util.convertAddRequestToEntity(request, product, client);
        CreditOrder savedOrder = saveOrder(creditOrder);
        log.info("Create credit order: {}", savedOrder);
        return util.convertToAddResponse(savedOrder, product, client);
    }

    public CreditOrder saveOrder(CreditOrder creditOrder) {
        return repository.save(creditOrder);
    }
}

package com.example.credit_service_project.services.creditOrder;

import com.example.credit_service_project.DTO.creditOrderDTO.AddCreditOrderDTORequest;
import com.example.credit_service_project.DTO.creditOrderDTO.AddCreditOrderResponseDTO;
import com.example.credit_service_project.entity.Client;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.repository.CreditOrderRepository;
import com.example.credit_service_project.services.client.ClientSearchService;
import com.example.credit_service_project.services.product.ProductSearchService;
import com.example.credit_service_project.services.utils.CreditOrderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditOrderCreateService {

    private final CreditOrderRepository repository;
    private final ProductSearchService searchProductService;
    private final ClientSearchService searchClientService;
    private final CreditOrderUtil util;

    public AddCreditOrderResponseDTO createCreditOrder(AddCreditOrderDTORequest request) {
        Product product = searchProductService.findById(request.getProductID());
        Client client = searchClientService.findClientById(request.getClientID());

        CreditOrder creditOrder = util.convertAddRequestToEntity(request, product, client);
        CreditOrder saved = saveOrder(creditOrder);

        return util.convertToAddResponse(saved, product, client);
    }

    public CreditOrder saveOrder(CreditOrder creditOrder) {
        return repository.save(creditOrder);
    }
}

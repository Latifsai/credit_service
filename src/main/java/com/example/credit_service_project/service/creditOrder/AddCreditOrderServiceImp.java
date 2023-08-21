package com.example.credit_service_project.service.creditOrder;

import com.example.credit_service_project.DTO.creditOrderDTO.AddCreditOrderDTORequest;
import com.example.credit_service_project.DTO.creditOrderDTO.AddCreditOrderResponseDTO;
import com.example.credit_service_project.entity.Client;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.entity.Product;
import com.example.credit_service_project.repository.CreditOrderRepository;
import com.example.credit_service_project.service.CreditOrderService;
import com.example.credit_service_project.service.client.SearchClientServiceImp;
import com.example.credit_service_project.service.product.SearchProductServiceImp;
import com.example.credit_service_project.service.utils.CreditOrderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddCreditOrderServiceImp implements CreditOrderService<AddCreditOrderResponseDTO, AddCreditOrderDTORequest> {

    private final CreditOrderRepository repository;
    private final SearchProductServiceImp searchProductService;
    private final SearchClientServiceImp searchClientService;
    private final CreditOrderUtil util;

    @Override
    public AddCreditOrderResponseDTO execute(AddCreditOrderDTORequest request) {
        Product product = searchProductService.findById(request.getProductID());

        Client client = searchClientService.findClientById(request.getClientID());

        CreditOrder creditOrder = util.convertAddRequestToEntity(request, product, client);
        CreditOrder saved = saveOrder(creditOrder);

        return util.convertToAddResponse(saved, product);
    }

    public CreditOrder saveOrder(CreditOrder creditOrder) {
        return repository.save(creditOrder);
    }
}

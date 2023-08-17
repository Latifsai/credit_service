package com.example.credit_service_project.fabrics.creditOrder;

import com.example.credit_service_project.repository.CreditOrderRepository;
import com.example.credit_service_project.service.client.SearchClientServiceImp;
import com.example.credit_service_project.service.creditOrder.*;
import com.example.credit_service_project.service.product.SearchProductServiceImp;
import com.example.credit_service_project.service.utils.CreditOrderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreditOrderFabricImp implements CreditOrderFabric {

    private final CreditOrderRepository repository;
    private final SearchProductServiceImp searchProductService;
    private final SearchCreditOrderServiceImp searchCreditOrderService;
    private final CreditOrderUtil util;
    private final AddCreditOrderServiceImp addCreditOrderService;
    private final SearchClientServiceImp searchClientService;
    private final GetAllCreditService getAllCreditService;

    @Override
    public AddCreditOrderServiceImp add() {
        return new AddCreditOrderServiceImp(repository, searchProductService, searchClientService, util);
    }

    @Override
    public GetAllCreditService getAll() {
        return new GetAllCreditService(repository, util);
    }

    @Override
    public SearchCreditOrderServiceImp search() {
        return new SearchCreditOrderServiceImp(repository, util);
    }

    @Override
    public UpdateCreditOrderServiceImp update() {
        return new UpdateCreditOrderServiceImp(addCreditOrderService, searchCreditOrderService, util);
    }

    @Override
    public ConsiderationOrderService considerationOrderService() {
        return new ConsiderationOrderService(getAllCreditService, repository, util);
    }
}

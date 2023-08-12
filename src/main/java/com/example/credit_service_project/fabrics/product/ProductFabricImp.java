package com.example.credit_service_project.fabrics.product;

import com.example.credit_service_project.repository.ProductRepository;
import com.example.credit_service_project.service.product.*;
import com.example.credit_service_project.service.utils.ProductUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductFabricImp implements ProductFabric {
    private final CreateProductServiceImp createProductService;
    private final SearchProductServiceImp searchProductService;
    private final ProductRepository repository;
    private final ProductUtil util;

    @Override
    public GetProductsListService getList() {
        return new GetProductsListService(repository, util);
    }

    @Override
    public UpdateProductServiceImp update() {
        return new UpdateProductServiceImp(createProductService, searchProductService, util);
    }

    @Override
    public DeleteProductServiceImp delete() {
        return new DeleteProductServiceImp(searchProductService, util);
    }

    @Override
    public CreateProductServiceImp create() {
        return new CreateProductServiceImp(repository, util);
    }

    @Override
    public SearchProductServiceImp search() {
        return new SearchProductServiceImp(repository, util);
    }
}

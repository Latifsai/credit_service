package com.example.credit_service_project.fabrics.product;

import com.example.credit_service_project.service.product.*;

public interface ProductFabric {

    GetProductsListService getList();
    UpdateProductServiceImp update();
    DeleteProductServiceImp delete();
    CreateProductServiceImp create();
    SearchProductServiceImp search();
}

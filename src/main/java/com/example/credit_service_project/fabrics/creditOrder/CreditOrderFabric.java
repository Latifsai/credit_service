package com.example.credit_service_project.fabrics.creditOrder;

import com.example.credit_service_project.service.creditOrder.*;

public interface CreditOrderFabric {
    AddCreditOrderServiceImp add();
    GetAllCreditService getAll();
    SearchCreditOrderServiceImp search();
    UpdateCreditOrderServiceImp update();
}

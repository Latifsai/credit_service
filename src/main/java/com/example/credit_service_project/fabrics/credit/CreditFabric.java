package com.example.credit_service_project.fabrics.credit;

import com.example.credit_service_project.DTO.creditDTO.AddCreditDTORequest;
import com.example.credit_service_project.DTO.creditDTO.AddCreditDTOResponse;
import com.example.credit_service_project.service.CreditService;
import com.example.credit_service_project.service.cerdit.GetAllCreditsService;

public interface CreditFabric {
    GetAllCreditsService get();

    CreditService<AddCreditDTOResponse, AddCreditDTORequest> add();
}

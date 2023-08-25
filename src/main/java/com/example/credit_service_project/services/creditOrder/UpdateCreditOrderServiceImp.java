package com.example.credit_service_project.services.creditOrder;

import com.example.credit_service_project.DTO.creditOrderDTO.CreditOrderResponseDTO;
import com.example.credit_service_project.DTO.creditOrderDTO.UpdateCreditOrderDTORequest;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.services.CreditOrderService;
import com.example.credit_service_project.services.utils.CreditOrderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCreditOrderServiceImp implements CreditOrderService<CreditOrderResponseDTO, UpdateCreditOrderDTORequest> {

    private final CreateCreditOrderServiceImp addCreditOrderService;
    private final SearchCreditOrderServiceImp searchCreditOrderService;
    private final CreditOrderUtil util;

    @Override
    public CreditOrderResponseDTO execute(UpdateCreditOrderDTORequest request) {
        CreditOrder creditOrder = searchCreditOrderService.findById(request.getId());
        CreditOrder updated = util.update(request, creditOrder);
        addCreditOrderService.saveOrder(updated);
        return util.convertToResponse(updated);
    }
}

package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.dto.agreementDTO.AgreementResponse;
import com.example.credit_service_project.dto.agreementDTO.CreateAgreementRequest;
import com.example.credit_service_project.entity.Agreement;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.entity.enums.CreditOrderStatus;
import com.example.credit_service_project.service.utils.generator.FieldsGenerator;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.AgreementException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AgreementUtil {
    public Agreement convertCreateRequestToEntity(CreateAgreementRequest request, CreditOrder order) {

        if (!order.getCreditOrderStatus().equals(CreditOrderStatus.APPROVED)) {
            throw new AgreementException(ErrorsMessage.AGREEMENT_CREATE_MESSAGE);
        }

        Agreement agreement = new Agreement();
        agreement.setNumber(FieldsGenerator.generateRandomNumber(request.getNumberLength()));
        agreement.setAgreementDate(LocalDate.now());
        agreement.setActive(true);
        agreement.setCreditOrderNumber(order.getNumber());
        return agreement;
    }

    public AgreementResponse convertToResponse(Agreement agreement) {
        return new AgreementResponse(
                agreement.getId(),
                agreement.getNumber(),
                agreement.getAgreementDate(),
                agreement.getTerminationDate(),
                agreement.isActive()
        );
    }
}

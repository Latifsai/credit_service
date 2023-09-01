package com.example.credit_service_project.services.utils;

import com.example.credit_service_project.DTO.creditDTO.CreateCreditDTORequest;
import com.example.credit_service_project.DTO.creditDTO.AddCreditDTOResponse;
import com.example.credit_service_project.DTO.creditDTO.CreditDTOResponse;
import com.example.credit_service_project.DTO.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Agreement;
import com.example.credit_service_project.entity.Credit;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.services.utils.generator.CreditGenerator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.example.credit_service_project.entity.enums.CreditStatus.ACTIVE;

@Service
public class CreditUtil {

    public Credit createCreditFromData(CreateCreditDTORequest request, Account account, Agreement agreement, CreditOrder creditOrder) {
        Credit credit = new Credit();

        credit.setAccount(account);
        credit.setAgreement(agreement);
        credit.setCreditOrder(creditOrder);
        credit.setFine(BigDecimal.ZERO);
        credit.setCreditType(request.getCreditType());
        credit.setCreditSum(creditOrder.getAmount());
        credit.setPeriodMonth(request.getPeriodMonth());
        credit.setInterestRate(CreditGenerator.getInterestRateByCountry(account.getCountry()));
        credit.setNeedDeposit(creditOrder.getProduct().isNeedGuaranty());
        credit.setCreditStatus(ACTIVE);
        credit.setCurrency(account.getCurrency());
        credit.setCreditHolidayMonthsAmount(request.getCreditHolidaysMonth());

        //save agrememt
        agreement.setTerminationDate(agreement.getAgreementDate().plusMonths(credit.getPeriodMonth()));
        //update account
        setDebtToAccount(account, credit);
        return credit;
    }

    private Account setDebtToAccount(Account account, Credit credit) {
        account.setLoanDebt(credit.getCreditSum());
        account.setPercentageDebt(credit.getInterestRate());
        account.setUnpaidCreditSum(credit.getCreditSum().add(credit.getFine()));
        return account;
    }

    public AddCreditDTOResponse convertResponse(Credit credit, List<PaymentResponseDTO> list) {
        return AddCreditDTOResponse.builder()
                .id(credit.getId())
                .creditType(credit.getCreditType())
                .creditSum(credit.getCreditSum())
                .periodMonth(credit.getPeriodMonth())
                .fine(credit.getFine())
                .needDeposit(credit.isNeedDeposit())
                .creditStatus(credit.getCreditStatus())
                .currency(credit.getCurrency())
                .accountNumber(credit.getAccount().getAccountNumber())
                .agreementNumber(credit.getAgreement().getNumber())
                .terminationDate(credit.getAgreement().getTerminationDate())
                .creditOrderNumber(credit.getCreditOrder().getNumber())
                .productID(credit.getCreditOrder().getProduct().getId())
                .productName(credit.getCreditOrder().getProduct().getName())
                .calculationType(credit.getCreditOrder().getProduct().getCalculationType())
                .list(list)
                .build();
    }

    public CreditDTOResponse convertToCreditResponse(Credit credit) {
        return CreditDTOResponse
                .builder()
                .id(credit.getId())
                .creditType(credit.getCreditType())
                .creditSum(credit.getCreditSum())
                .interestRate(credit.getInterestRate())
                .periodMonth(credit.getPeriodMonth())
                .fine(credit.getFine())
                .needDeposit(credit.isNeedDeposit())
                .creditStatus(credit.getCreditStatus())
                .currency(credit.getCurrency())
                .build();
    }
}

package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.DTO.creditDTO.AddCreditDTORequest;
import com.example.credit_service_project.DTO.creditDTO.AddCreditDTOResponse;
import com.example.credit_service_project.DTO.creditDTO.CreditDTOResponse;
import com.example.credit_service_project.DTO.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Agreement;
import com.example.credit_service_project.entity.Credit;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.service.utils.generator.CreditGenerator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

import static com.example.credit_service_project.entity.enums.CreditStatus.ACTIVE;

@Component
public class CreditUtil {

    public Credit createCreditFromData(AddCreditDTORequest request, Account account, Agreement agreement, CreditOrder creditOrder) {
        Credit credit = new Credit();

        credit.setAccount(account);
        credit.setAgreement(agreement);
        credit.setCreditOrder(creditOrder);

        credit.setCreditType(request.getCreditType());
        credit.setCreditSum(creditOrder.getAmount());
        credit.setPeriodMonth(request.getPeriodMonth());
        credit.setInterestRate(CreditGenerator.getInterestRateByCountry(account.getCountry()));
        credit.setFine(BigDecimal.valueOf(0.1)); // разобраться с пеней
        credit.setNeedDeposit(creditOrder.getProduct().isNeedGuaranty());
        credit.setCreditStatus(ACTIVE);
        credit.setCurrency(account.getCurrency());

        //save agrememt
        agreement.setTerminationDate(agreement.getAgreementDate().plusMonths(credit.getPeriodMonth()));
        //update account
        setDebtToAccount(account, credit);
        return credit;
    }

    private Account setDebtToAccount(Account account, Credit credit) {
        account.setLoanDebt(credit.getCreditSum());
        account.setPercentageDebt(credit.getInterestRate());
        account.setUnpaidLoanDebt(credit.getCreditSum());
        account.setUnpaidPercentageLoanDebt(credit.getInterestRate());
        return account;
    }

    public AddCreditDTOResponse convertResponse(Credit credit, List<PaymentResponseDTO> list) {
        return new AddCreditDTOResponse(
                credit.getId(),
                credit.getCreditType(),
                credit.getCreditSum(),
                credit.getPeriodMonth(),
                credit.getFine(),
                credit.isNeedDeposit(),
                credit.getCreditStatus(),
                credit.getCurrency(),
                credit.getAccount().getAccountNumber(),
                credit.getAgreement().getNumber(),
                credit.getAgreement().getTerminationDate(),
                credit.getCreditOrder().getNumber(),
                credit.getCreditOrder().getProduct().getId(),
                credit.getCreditOrder().getProduct().getName(),
                credit.getCreditOrder().getProduct().getCalculationType(),
                list
        );
    }

    public CreditDTOResponse convertToCreditResponse(Credit credit) {
        return new CreditDTOResponse(
                credit.getId(),
                credit.getCreditType(),
                credit.getCreditSum(),
                credit.getInterestRate(),
                credit.getPeriodMonth(),
                credit.getFine(),
                credit.isNeedDeposit(),
                credit.getCreditStatus(),
                credit.getCurrency()
        );
    }
}

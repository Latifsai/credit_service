package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.dto.creditDTO.CreateCreditDTOResponse;
import com.example.credit_service_project.dto.creditDTO.CreateCreditRequestDTO;
import com.example.credit_service_project.dto.creditDTO.CreditResponseDTO;
import com.example.credit_service_project.dto.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Agreement;
import com.example.credit_service_project.entity.Credit;
import com.example.credit_service_project.entity.CreditOrder;
import com.example.credit_service_project.service.utils.generator.CreditGenerator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static com.example.credit_service_project.entity.enums.CreditStatus.ACTIVE;

@Service
public class CreditUtil {

    public Credit createCreditFromData(CreateCreditRequestDTO request, Account account, Agreement agreement, CreditOrder creditOrder) {
        Credit credit = new Credit();

        credit.setAccount(account);
        credit.setAgreement(agreement);
        credit.setCreditOrder(creditOrder);
        credit.setFine(BigDecimal.ZERO);
        credit.setInterestRate(CreditGenerator.getInterestRateByCountry(account.getCountry()));
        credit.setCreditType(request.getCreditType());
        credit.setCreditSum(getTotalCreditAmount(creditOrder.getAmount(), credit.getInterestRate(), request.getPeriodMonth()));
        credit.setPeriodMonth(request.getPeriodMonth());
        credit.setNeedDeposit(creditOrder.getProduct().isNeedGuaranty());
        credit.setCreditStatus(ACTIVE);
        credit.setCurrency(account.getCurrency());
        credit.setCreditHolidayMonthsAmount(request.getCreditHolidaysMonth());

        agreement.setTerminationDate(agreement.getAgreementDate().plusMonths(credit.getPeriodMonth()));

        setDebtToAccount(account, credit);
        return credit;
    }

    private BigDecimal getTotalCreditAmount(BigDecimal loanAmount, BigDecimal interestRate, int numberOfMonths) {

        BigDecimal monthlyInterestRate = interestRate.divide(BigDecimal.valueOf(12 * 100), 6, RoundingMode.HALF_EVEN);

        BigDecimal totalInterestAmount = loanAmount.multiply(monthlyInterestRate).multiply(BigDecimal.valueOf(numberOfMonths));

        BigDecimal totalLoanAmount = loanAmount.add(totalInterestAmount);

        return totalLoanAmount.setScale(2, RoundingMode.HALF_UP);
    }

    private void setDebtToAccount(Account account, Credit credit) {
        account.setLoanDebt(credit.getCreditSum());
        account.setPercentageDebt(credit.getInterestRate());
        account.setUnpaidCreditSum(credit.getCreditSum().add(credit.getFine()));
    }

    public CreateCreditDTOResponse convertResponse(Credit credit, List<PaymentResponseDTO> list) {
        return CreateCreditDTOResponse.builder()
                .id(credit.getId())
                .creditType(credit.getCreditType())
                .creditSum(credit.getCreditSum())
                .interestRate(credit.getInterestRate())
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

    public CreditResponseDTO convertToCreditResponse(Credit credit) {
        return CreditResponseDTO
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

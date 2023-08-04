package com.example.credit_service_project.serviceTest.generators;

import com.example.credit_service_project.DTO.accountDTO.*;
import com.example.credit_service_project.entity.enums.AccountStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static java.time.Month.APRIL;

public class DTOAccountCreator {

    public static AddAccountDTOResponse createAddResponse() {
        AddAccountDTOResponse response = new AddAccountDTOResponse();
        response.setId(UUID.fromString("00009999-2222-1111-a456-426655440000"));
        response.setAccountNumber("A10B3U3OI9");
        response.setLoanDebt(new BigDecimal("2500"));
        response.setPercentageDebt(new BigDecimal("250"));
        response.setStatus(AccountStatus.ACTIVE);
        response.setBalance(new BigDecimal("3000"));
        response.setOpeningDate(LocalDate.of(2020, APRIL, 21));
        response.setClosingDate(LocalDate.of(2027, APRIL, 30));
        response.setUnpaidLoanDebt(new BigDecimal("1300"));
        response.setUnpaidPercentageLoanDebt(new BigDecimal("157"));
        response.setCurrency("$");
        return response;
    }

    public static AddAccountDTORequest createRequest() {
        var request = new AddAccountDTORequest();
        request.setLoanDebt(new BigDecimal("2500"));
        request.setPercentageDebt(new BigDecimal("250"));
        request.setBalance(new BigDecimal("3000"));
        request.setCurrency("$");
        request.setStatus(AccountStatus.ACTIVE);
        request.setAccountNumberLength(10);
        request.setYearsAmountForClosingDate(7L);
        return request;
    }

    public static AddAccountDTORequest createRequestWithExceptions() {
        var request = new AddAccountDTORequest();
        request.setLoanDebt(new BigDecimal("-2000"));
        request.setPercentageDebt(new BigDecimal("-200"));
        request.setBalance(new BigDecimal("-10"));
        request.setCurrency(null);
        request.setStatus(AccountStatus.ACTIVE);
        request.setAccountNumberLength(10);
        request.setYearsAmountForClosingDate(7L);
        return request;
    }
    
    public static AccountResponseDTO getSearchResponse() {
        var response = new AccountResponseDTO();
        response.setId(UUID.fromString("00009999-2222-1111-a456-426655440000"));
        response.setAccountNumber("A10B3U3OI9");
        response.setStatus(AccountStatus.ACTIVE);
        response.setBalance(new BigDecimal("3000"));
        response.setLoanDebt(new BigDecimal("2500"));
        response.setUnpaidLoanDebt(new BigDecimal("1300"));
        response.setUnpaidPercentageLoanDebt(new BigDecimal("157"));
        response.setCurrency("$");
        return response;
    }

    public static UpdateAccountRequest getUpdateRequest() {
        var request = new UpdateAccountRequest();
        request.setAccountNumber("A10B3U3OI9");
        request.setLoanDebt(null);
        request.setPercentageDebt(null);
        request.setStatus(AccountStatus.ACTIVE);
        request.setBalance(new BigDecimal("5000"));
        request.setUnpaidLoanDebt(new BigDecimal("2000"));
        request.setUnpaidPercentageLoanDebt(null);
        return request;
    }

    public static UpdateAccountResponse getUpdateResponse() {
        var response = new UpdateAccountResponse();
        response.setId(UUID.fromString("00009999-2222-1111-a456-426655440000"));
        response.setAccountNumber("A10B3U3OI9");
        response.setLoanDebt(new BigDecimal("2500"));
        response.setPercentageDebt(new BigDecimal("250"));
        response.setStatus(AccountStatus.ACTIVE);
        response.setBalance(new BigDecimal("5000"));
        response.setUnpaidLoanDebt(new BigDecimal("2000"));
        response.setUnpaidPercentageLoanDebt(new BigDecimal("157"));
        response.setCurrency("$");
        return response;
    }

}

package com.example.credit_service_project.serviceTest.generators;

import com.example.credit_service_project.DTO.accountDTO.AccountDTOResponse;
import com.example.credit_service_project.DTO.accountDTO.AddAccountDTORequest;
import com.example.credit_service_project.DTO.accountDTO.UpdateAccountRequest;
import com.example.credit_service_project.entity.enums.AccountStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static java.time.Month.APRIL;

public class DTOAccountCreator {

    public static AccountDTOResponse createDTOResponse() {
        return new AccountDTOResponse(
                UUID.fromString("00009999-2222-1111-a456-426655440000"),
                "A10B3U3OI9",
                "John Tate",
                new BigDecimal("2500"),
                new BigDecimal("250"),
                AccountStatus.ACTIVE,
                new BigDecimal("3000"),
                LocalDate.of(2027, APRIL, 30),
                new BigDecimal("1300"),
                new BigDecimal("157"),
                "$"
        );
    }

    public static AddAccountDTORequest createRequest() {
        return new AddAccountDTORequest(
                UUID.fromString("00009999-2222-1111-a456-426655440000"),
                new BigDecimal("2500"),
                new BigDecimal("250"),
                new BigDecimal("3000"),
                "$",
                AccountStatus.ACTIVE,
                10,
                7
        );
    }

    public static AddAccountDTORequest getRequestWithExceptions() {
        var request = new AddAccountDTORequest();
        request.setLoanDebt(new BigDecimal("-2000"));
        request.setPercentageDebt(new BigDecimal("-200"));
        request.setBalance(new BigDecimal("-10"));
        request.setCurrency(null);
        request.setStatus(AccountStatus.ACTIVE);
        request.setAccountNumberLength(10);
        request.setYearsAmountForClosingDate(3);
        return request;
    }


    public static UpdateAccountRequest getUpdateRequest() {
        return new UpdateAccountRequest(
                UUID.fromString("00009999-2222-1111-a456-426655440000"),
                "A10B3U3OI9",
                null,
                null,
                AccountStatus.ACTIVE,
                new BigDecimal("5000"),
                new BigDecimal("2000"),
                new BigDecimal("200")
                );
    }

    public static AccountDTOResponse getUpdatedDTOResponse() {
        return new AccountDTOResponse(
                UUID.fromString("00009999-2222-1111-a456-426655440000"),
                "A10B3U3OI9",
                "John Tate",
                new BigDecimal("2500"),
                new BigDecimal("250"),
                AccountStatus.ACTIVE,
                new BigDecimal("5000"),
                LocalDate.of(2027, APRIL, 30),
                new BigDecimal("2000"),
                new BigDecimal("200"),
                "$"
        );
    }

    public static UpdateAccountRequest getUpdateRequestWithErrors() {
        return new UpdateAccountRequest(
                null,
                "",
                null,
                null,
                AccountStatus.ACTIVE,
                new BigDecimal("5000"),
                new BigDecimal("2000"),
                new BigDecimal("200")
        );
    }
}

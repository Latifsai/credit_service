package com.example.credit_service_project.controllers;

import com.example.credit_service_project.DTO.accountDTO.AccountResponseDTO;
import com.example.credit_service_project.DTO.accountDTO.SearchAccountRequest;
import com.example.credit_service_project.DTO.agreementDTO.AgreementResponse;
import com.example.credit_service_project.DTO.cardDTO.CardResponseDTO;
import com.example.credit_service_project.DTO.creditOrderDTO.CheckCreditOrderStatusResponse;
import com.example.credit_service_project.DTO.creditOrderDTO.CreditOrderResponseDTO;
import com.example.credit_service_project.DTO.operationDTO.GetBelongsAccountOperationsRequest;
import com.example.credit_service_project.DTO.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.DTO.operationDTO.PreliminaryCalculationRequest;
import com.example.credit_service_project.DTO.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.DTO.paymentDTO.PreliminaryCalculationResponse;
import com.example.credit_service_project.services.account.AccountSearchService;
import com.example.credit_service_project.services.agreement.SearchAgreementServiceImp;
import com.example.credit_service_project.services.card.CardSearchService;
import com.example.credit_service_project.services.credit.CheckUnpaidPaymentsBelongsCreditService;
import com.example.credit_service_project.services.creditOrder.CheckCreditOrderStatusService;
import com.example.credit_service_project.services.creditOrder.CreditOrderSearchService;
import com.example.credit_service_project.services.operation.GetOperationsService;
import com.example.credit_service_project.services.operation.OperationSearchService;
import com.example.credit_service_project.services.product.GetPreliminaryCalculationOfProduct;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * All request here can be used be Client and Manager
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/entered")
public class SearchController {

    private final CardSearchService cardSearch;
    private final AccountSearchService accountSearch;
    private final OperationSearchService operationSearch;
    private final GetOperationsService getAllOperations;
    private final CheckUnpaidPaymentsBelongsCreditService checkUnpaidPaymentsBelongsCreditService;
    private final CheckCreditOrderStatusService checkCreditOrderStatus;
    private final CreditOrderSearchService searchOrder;
    private final SearchAgreementServiceImp searchAgreement;
    private final GetPreliminaryCalculationOfProduct getPreliminaryCalculationOfProduct;


    @GetMapping("/account/search")
    @ResponseStatus(HttpStatus.FOUND)
    public AccountResponseDTO searchAccount(@RequestBody SearchAccountRequest request) {
        return accountSearch.searchAccount(request);
    }

    @GetMapping("/card/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public CardResponseDTO searchCard(@PathVariable("id") @NotNull UUID id) {
        return cardSearch.searchCard(id);
    }

    @GetMapping("/operations")
    @ResponseStatus(HttpStatus.FOUND)
    public List<OperationResponseDTO> get(@RequestBody GetBelongsAccountOperationsRequest request) {
        return getAllOperations.getAllOperations(request);
    }

    @GetMapping("/operations/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public OperationResponseDTO search(@PathVariable("id") @NotNull UUID id) {
        return operationSearch.searchOperation(id);
    }

    @GetMapping("/credit/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<PaymentResponseDTO> getUnpaidPayments(@PathVariable("id") UUID id) {
        return checkUnpaidPaymentsBelongsCreditService.checkUnpaidPaymentsBelongsCredit(id);
    }

    @GetMapping("/order/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public CreditOrderResponseDTO find(@PathVariable("id") @NotNull UUID id) {
        return searchOrder.searchCreditOrder(id);
    }

    @GetMapping("/check/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public CheckCreditOrderStatusResponse checkStatus(@PathVariable("id") @NotNull UUID id) {
        return checkCreditOrderStatus.checkOrderStatus(id);
    }

    @GetMapping("/agreement/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public AgreementResponse findAgreement(@PathVariable(name = "id") UUID id) {
        return searchAgreement.searchAgreement(id);
    }

    @GetMapping("/credit_preview")
    @ResponseStatus(HttpStatus.OK)
    public List<PreliminaryCalculationResponse> showPreliminary(@RequestBody PreliminaryCalculationRequest request) {
        return getPreliminaryCalculationOfProduct.getPreliminaryCalculation(request);
    }
}

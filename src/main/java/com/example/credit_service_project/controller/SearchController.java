package com.example.credit_service_project.controller;

import com.example.credit_service_project.dto.CreditHistoryResponse;
import com.example.credit_service_project.dto.accountDTO.AccountResponseDTO;
import com.example.credit_service_project.dto.accountDTO.SearchAccountRequest;
import com.example.credit_service_project.dto.agreementDTO.AgreementResponse;
import com.example.credit_service_project.dto.cardDTO.CardResponseDTO;
import com.example.credit_service_project.dto.creditOrderDTO.CheckCreditOrderStatusResponse;
import com.example.credit_service_project.dto.creditOrderDTO.CreditOrderResponseDTO;
import com.example.credit_service_project.dto.delayDTO.DelayResponse;
import com.example.credit_service_project.dto.operationDTO.GetBelongsAccountOperationsRequest;
import com.example.credit_service_project.dto.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.dto.operationDTO.PreliminaryCalculationRequest;
import com.example.credit_service_project.dto.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.dto.paymentDTO.PreliminaryCalculationResponse;
import com.example.credit_service_project.service.account.AccountSearchService;
import com.example.credit_service_project.service.agreement.SearchAgreementService;
import com.example.credit_service_project.service.card.CardSearchService;
import com.example.credit_service_project.service.credit.CheckUnpaidPaymentsBelongsCreditService;
import com.example.credit_service_project.service.creditHistory.CreditHistoryService;
import com.example.credit_service_project.service.creditOrder.CheckCreditOrderStatusService;
import com.example.credit_service_project.service.creditOrder.CreditOrderSearchService;
import com.example.credit_service_project.service.delay.DelayService;
import com.example.credit_service_project.service.operation.GetOperationsService;
import com.example.credit_service_project.service.operation.OperationSearchService;
import com.example.credit_service_project.service.product.GetPreliminaryCalculationOfProduct;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * All request here can be used by Client and Manager
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
    private final SearchAgreementService searchAgreement;
    private final GetPreliminaryCalculationOfProduct getPreliminaryCalculationOfProduct;
    private final CreditHistoryService creditHistoryService;
    private final DelayService delayService;

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
    public List<OperationResponseDTO> getBelongsAccountOperations(@RequestBody GetBelongsAccountOperationsRequest request) {
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
    public CreditOrderResponseDTO findCreditOrder(@PathVariable("id") @NotNull UUID id) {
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

    @GetMapping("/credit_history/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public CreditHistoryResponse findCreditHistoryByID(@PathVariable(name = "id") UUID id) {
        return creditHistoryService.findByID(id);
    }

    @GetMapping("/delays/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<DelayResponse> findAllDelaysBelongsCreditHistory(@PathVariable(name = "id") UUID historyId) {
        return delayService.findAllDelaysBelongsToCreditHistory(historyId);
    }
}

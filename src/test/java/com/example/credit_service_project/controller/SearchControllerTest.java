package com.example.credit_service_project.controller;

import com.example.credit_service_project.dto.CreditHistoryResponse;
import com.example.credit_service_project.dto.accountDTO.AccountResponseDTO;
import com.example.credit_service_project.dto.accountDTO.SearchAccountRequest;
import com.example.credit_service_project.dto.agreementDTO.AgreementResponse;
import com.example.credit_service_project.dto.cardDTO.CardResponseDTO;
import com.example.credit_service_project.dto.creditOrderDTO.CheckCreditOrderStatusResponse;
import com.example.credit_service_project.dto.delayDTO.DelayResponse;
import com.example.credit_service_project.dto.operationDTO.GetBelongsAccountOperationsRequest;
import com.example.credit_service_project.dto.operationDTO.OperationResponseDTO;
import com.example.credit_service_project.dto.operationDTO.PreliminaryCalculationRequest;
import com.example.credit_service_project.dto.paymentDTO.PaymentResponseDTO;
import com.example.credit_service_project.dto.paymentDTO.PreliminaryCalculationResponse;
import com.example.credit_service_project.entity.enums.CreditOrderStatus;
import com.example.credit_service_project.generators.*;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class SearchControllerTest {

    @MockBean
    private CardSearchService cardSearch;
    @MockBean
    private AccountSearchService accountSearch;
    @MockBean
    private OperationSearchService operationSearch;
    @MockBean
    private GetOperationsService getAllOperations;
    @MockBean
    private CheckUnpaidPaymentsBelongsCreditService checkUnpaidPaymentsBelongsCreditService;
    @MockBean
    private CheckCreditOrderStatusService checkCreditOrderStatus;
    @MockBean
    private CreditOrderSearchService searchOrder;
    @MockBean
    private SearchAgreementService searchAgreement;
    @MockBean
    private GetPreliminaryCalculationOfProduct getPreliminaryCalculationOfProduct;
    @MockBean
    private CreditHistoryService creditHistoryService;
    @MockBean
    private DelayService delayService;
    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Test searchAccount method")
    @WithMockUser(value = "Oleg", roles = {"CLIENT"})
    void searchAccount() throws Exception {

        AccountResponseDTO response = AccountDTOGenerator.getResponse();
        SearchAccountRequest request = new SearchAccountRequest(UUID.randomUUID(), "A32G$");

        when(accountSearch.searchAccount(request)).thenReturn(response);

        mockMvc.perform(get("/entered/account/search")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.accountNumber").exists())
                .andExpect(jsonPath("$.clientInitial").value(response.getClientInitial()))
                .andExpect(jsonPath("$.balance").value(response.getBalance()));
    }

    @Test
    @DisplayName("Test searchAccountForbidden method")
    void searchAccountForbidden() throws Exception {

        AccountResponseDTO response = AccountDTOGenerator.getResponse();
        SearchAccountRequest request = new SearchAccountRequest(UUID.randomUUID(), "A32G$");

        when(accountSearch.searchAccount(request)).thenReturn(response);

        mockMvc.perform(get("/entered/account/search")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Test searchCard method")
    @WithMockUser(value = "Oleg", roles = {"CLIENT"})
    void searchCard() throws Exception {

        CardResponseDTO response = CardDTOGenerator.getCardResponse();
        UUID id = UUID.fromString("38859752-e264-43e0-ae34-02acef9e6061");

        when(cardSearch.searchCard(id)).thenReturn(response);

        mockMvc.perform(get("/entered/card/{id}", id))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.cardNumber").isString())
                .andExpect(jsonPath("$.iban").value(response.getIBAN()))
                .andExpect(jsonPath("$.balance").value(response.getBalance()))
                .andExpect(jsonPath("$.currency").value(response.getCurrency()));
    }

    @Test
    @DisplayName("Test searchCardForbidden method")
    void searchCardForbidden() throws Exception {

        UUID id = UUID.fromString("38859752-e264-43e0-ae34-02acef9e6061");

        mockMvc.perform(get("/entered/card/{id}", id))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(value = "Oleg", roles = {"CLIENT"})
    @DisplayName("Test getBelongsAccountOperations method")
    void getBelongsAccountOperations() throws Exception {
        List<OperationResponseDTO> response = Collections.singletonList(OperationDTOGenerator.getOperationResponseDTO());
        GetBelongsAccountOperationsRequest request = new GetBelongsAccountOperationsRequest(
                UUID.randomUUID(), "FH654JF");

        when(getAllOperations.getAllOperations(request)).thenReturn(response);

        mockMvc.perform(get("/entered/operations")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @DisplayName("Test getBelongsAccountOperationsForbidden method")
    void getBelongsAccountOperationsForbidden() throws Exception {
        List<OperationResponseDTO> response = Collections.singletonList(OperationDTOGenerator.getOperationResponseDTO());
        GetBelongsAccountOperationsRequest request = new GetBelongsAccountOperationsRequest(
                UUID.randomUUID(), "FH654JF");

        when(getAllOperations.getAllOperations(request)).thenReturn(response);

        mockMvc.perform(get("/entered/operations")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Test search method")
    @WithMockUser(value = "Oleg", roles = {"CLIENT"})
    void search() throws Exception {
        UUID id = UUID.fromString("38859752-e264-43e0-ae34-02acef9e6061");
        OperationResponseDTO response = OperationDTOGenerator.getOperationResponseDTO();
        when(operationSearch.searchOperation(id)).thenReturn(response);

        mockMvc.perform(get("/entered/operations/{id}", id))
                .andExpect(status().isFound());
    }

    @Test
    @DisplayName("Test searchForbidden method")
    void searchForbidden() throws Exception {
        UUID id = UUID.fromString("38859752-e264-43e0-ae34-02acef9e6061");
        OperationResponseDTO response = OperationDTOGenerator.getOperationResponseDTO();
        when(operationSearch.searchOperation(id)).thenReturn(response);

        mockMvc.perform(get("/entered/operations/{id}", id))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(value = "Oleg", roles = {"CLIENT"})
    @DisplayName("Test getUnpaidPayments method")
    void getUnpaidPayments() throws Exception {
        UUID id = UUID.fromString("38859752-e264-43e0-ae34-02acef9e6061");
        List<PaymentResponseDTO> response = Collections.singletonList(PaymentDTOGenerator.getPaymentResponseDTO());

        when(checkUnpaidPaymentsBelongsCreditService.checkUnpaidPaymentsBelongsCredit(id)).thenReturn(response);

        mockMvc.perform(get("/entered/credit/{id}", id))
                .andExpect(status().isFound());
    }

    @Test
    @DisplayName("Test getUnpaidPaymentsForbidden method")
    void getUnpaidPaymentsForbidden() throws Exception {
        UUID id = UUID.fromString("38859752-e264-43e0-ae34-02acef9e6061");
        List<PaymentResponseDTO> response = Collections.singletonList(PaymentDTOGenerator.getPaymentResponseDTO());

        when(checkUnpaidPaymentsBelongsCreditService.checkUnpaidPaymentsBelongsCredit(id)).thenReturn(response);

        mockMvc.perform(get("/entered/credit/{id}", id))
                .andExpect(status().isForbidden());
    }


    @Test
    @DisplayName("Test findCreditOrder method")
    @WithMockUser(value = "Oleg", roles = {"CLIENT"})
    void findCreditOrder() throws Exception {
        UUID id = UUID.fromString("38859752-e264-43e0-ae34-02acef9e6061");

        when(searchOrder.searchCreditOrder(id)).thenReturn(CreditOrderedGenerator.getResponse());

        mockMvc.perform(get("/entered/order/{id}", id))
                .andExpect(status().isFound());
    }

    @Test
    @DisplayName("Test findCreditOrderForBidden method")
    void findCreditOrderForBidden() throws Exception {
        UUID id = UUID.fromString("38859752-e264-43e0-ae34-02acef9e6061");

        when(searchOrder.searchCreditOrder(id)).thenReturn(CreditOrderedGenerator.getResponse());

        mockMvc.perform(get("/entered/order/{id}", id))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Test checkStatus method")
    @WithMockUser(value = "Oleg", roles = {"CLIENT"})
    void checkStatus() throws Exception {
        UUID id = UUID.fromString("38859752-e264-43e0-ae34-02acef9e6061");
        CheckCreditOrderStatusResponse response = new CheckCreditOrderStatusResponse(UUID.randomUUID(), CreditOrderStatus.IN_REVIEW, LocalDate.now(), 3);

        when(checkCreditOrderStatus.checkOrderStatus(id)).thenReturn(response);

        mockMvc.perform(get("/entered/check/{id}", id))
                .andExpect(status().isFound());
    }

    @Test
    @DisplayName("Test checkStatusForbidden method")
    void checkStatusForbidden() throws Exception {
        UUID id = UUID.fromString("38859752-e264-43e0-ae34-02acef9e6061");
        CheckCreditOrderStatusResponse response = new CheckCreditOrderStatusResponse(UUID.randomUUID(), CreditOrderStatus.IN_REVIEW, LocalDate.now(), 3);

        when(checkCreditOrderStatus.checkOrderStatus(id)).thenReturn(response);

        mockMvc.perform(get("/entered/check/{id}", id))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Test findAgreement method")
    @WithMockUser(value = "Oleg", roles = {"CLIENT"})
    void findAgreement() throws Exception {
        UUID id = UUID.fromString("38859752-e264-43e0-ae34-02acef9e6061");
        AgreementResponse response = AgreementDTOGenerator.getResponse();

        when(searchAgreement.searchAgreement(id)).thenReturn(response);

        mockMvc.perform(get("/entered/agreement/{id}", id))
                .andExpect(status().isFound());
    }

    @Test
    @DisplayName("Test findAgreementForbidden method")
    void findAgreementForbidden() throws Exception {
        UUID id = UUID.fromString("38859752-e264-43e0-ae34-02acef9e6061");
        AgreementResponse response = AgreementDTOGenerator.getResponse();

        when(searchAgreement.searchAgreement(id)).thenReturn(response);

        mockMvc.perform(get("/entered/agreement/{id}", id))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Test showPreliminary method")
    @WithMockUser(value = "Oleg", roles = {"CLIENT"})
    void showPreliminary() throws Exception {
        PreliminaryCalculationRequest request = new PreliminaryCalculationRequest(UUID.randomUUID(), "FG43874G",
                1L, 1, new BigDecimal("5.33"));
        var response = Collections.singletonList(new PreliminaryCalculationResponse(1, LocalDate.now(), BigDecimal.valueOf(1000), "USD"));

        when(getPreliminaryCalculationOfProduct.getPreliminaryCalculation(request)).thenReturn(response);

        mockMvc.perform(get("/entered/credit_preview")
                        .content(mapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].paymentNumber").isNumber());
    }

    @Test
    @DisplayName("Test showPreliminaryForBidden method")
    void showPreliminaryForBidden() throws Exception {
        PreliminaryCalculationRequest request = new PreliminaryCalculationRequest(UUID.randomUUID(), "FG43874G",
                1L, 1, new BigDecimal("5.33"));
        var response = Collections.singletonList(new PreliminaryCalculationResponse(1, LocalDate.now(), BigDecimal.valueOf(1000), "USD"));

        when(getPreliminaryCalculationOfProduct.getPreliminaryCalculation(request)).thenReturn(response);

        mockMvc.perform(get("/entered/credit_preview")
                        .content(mapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Test findCreditHistoryByID method")
    @WithMockUser(value = "Oleg", roles = {"CLIENT"})
    void findCreditHistoryByID() throws Exception {
        UUID id = UUID.fromString("c076896f-f0da-43ca-a10d-a9287fd95d5f");
        CreditHistoryResponse response = CreditHistoryDTOGenerator.getResponse();

        when(creditHistoryService.findByID(id)).thenReturn(response);

        mockMvc.perform(get("/entered/credit_history/{id}", id)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isFound());
    }

    @Test
    @DisplayName("Test findCreditHistoryByIDForBidden method")
    void findCreditHistoryByIDForBidden() throws Exception {
        UUID id = UUID.fromString("c076896f-f0da-43ca-a10d-a9287fd95d5f");
        CreditHistoryResponse response = CreditHistoryDTOGenerator.getResponse();

        when(creditHistoryService.findByID(id)).thenReturn(response);

        mockMvc.perform(get("/entered/credit_history/{id}", id)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Test findAllDelaysBelongsCreditHistory method")
    @WithMockUser(value = "Oleg", roles = {"CLIENT"})
    void findAllDelaysBelongsCreditHistory() throws Exception {
        UUID id = UUID.fromString("c076896f-f0da-43ca-a10d-a9287fd95d5f");
        List<DelayResponse> response = Collections.singletonList(DelayDTOGenerator.getResponse());

        when(delayService.findAllDelaysBelongsToCreditHistory(id)).thenReturn(response);

        mockMvc.perform(get("/entered//delays/{id}", id)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isFound());
    }

    @Test
    @DisplayName("Test findAllDelaysBelongsCreditHistoryForBidden method")
    void findAllDelaysBelongsCreditHistoryForBidden() throws Exception {
        UUID id = UUID.fromString("c076896f-f0da-43ca-a10d-a9287fd95d5f");
        List<DelayResponse> response = Collections.singletonList(DelayDTOGenerator.getResponse());

        when(delayService.findAllDelaysBelongsToCreditHistory(id)).thenReturn(response);

        mockMvc.perform(get("/entered//delays/{id}", id)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
}
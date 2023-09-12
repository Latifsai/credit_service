package com.example.credit_service_project.controllers;

import com.example.credit_service_project.configurations.JwtRequestFilter;
import com.example.credit_service_project.configurations.SecurityConfiguration;
import com.example.credit_service_project.services.account.AccountSearchService;
import com.example.credit_service_project.services.agreement.SearchAgreementService;
import com.example.credit_service_project.services.card.CardSearchService;
import com.example.credit_service_project.services.credit.CheckUnpaidPaymentsBelongsCreditService;
import com.example.credit_service_project.services.creditOrder.CheckCreditOrderStatusService;
import com.example.credit_service_project.services.creditOrder.CreditOrderSearchService;
import com.example.credit_service_project.services.operation.GetOperationsService;
import com.example.credit_service_project.services.operation.OperationSearchService;
import com.example.credit_service_project.services.product.GetPreliminaryCalculationOfProduct;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(SearchController.class)
@Import(SecurityConfiguration.class)
class SearchControllerTest {
    @MockBean
    private JwtRequestFilter filter;
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
    @Autowired
    private MockMvc mockMvc;


    @Test
    void searchAccount() throws Exception {
        mockMvc.perform(get("/entered/account/search"))
                .andExpect(status().isOk());
    }

    @Test
    void searchCard() throws Exception {
        mockMvc.perform(get("/entered/card/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getBelongsAccountOperations() throws Exception {
        mockMvc.perform(get("/entered/operations"))
                .andExpect(status().isOk());
    }

    @Test
    void search() throws Exception {
        mockMvc.perform(get("/entered/operations/1"))
                .andExpect(status().isOk());

    }

    @Test
    void getUnpaidPayments() throws Exception {
        mockMvc.perform(get("/entered/credit/1"))
                .andExpect(status().isOk());
    }

    @Test
    void findCreditOrder() throws Exception {
        mockMvc.perform(get("/entered/order/1"))
                .andExpect(status().isOk());
    }

    @Test
    void checkStatus() throws Exception {
        mockMvc.perform(get("/entered/check/1"))
                .andExpect(status().isOk());
    }

    @Test
    void findAgreement() throws Exception {
        mockMvc.perform(get("/entered/agreement/1"))
                .andExpect(status().isOk());
    }

    @Test
    void showPreliminary() throws Exception {
        mockMvc.perform(get("/entered/credit_preview"))
                .andExpect(status().isOk());
    }
}
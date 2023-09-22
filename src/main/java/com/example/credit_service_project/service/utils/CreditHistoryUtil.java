package com.example.credit_service_project.service.utils;

import com.example.credit_service_project.dto.creditHistoryDTO.CreditHistoryResponse;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.CreditHistory;
import com.example.credit_service_project.entity.enums.CreditHistoryStatus;
import org.springframework.stereotype.Service;

@Service
public class CreditHistoryUtil {

    public CreditHistory createHistoryFromAccount(Account account) {
        CreditHistory creditHistory = new CreditHistory();
        creditHistory.setAccount(account);
        creditHistory.setStatus(CreditHistoryStatus.FLAWLESS);
        return creditHistory;
    }

    public CreditHistoryResponse convertEntityToResponse(CreditHistory creditHistory) {
        return CreditHistoryResponse.builder()
                .id(creditHistory.getId())
                .status(creditHistory.getStatus())
                .delayAmount(creditHistory.getDelays().size())
                .accountNumber(creditHistory.getAccount().getAccountNumber())
                .build();
    }
}

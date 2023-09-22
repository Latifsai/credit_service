package com.example.credit_service_project.service.delay;

import com.example.credit_service_project.dto.delayDTO.DelayResponse;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.CreditHistory;
import com.example.credit_service_project.entity.Delay;
import com.example.credit_service_project.repository.DelayRepository;
import com.example.credit_service_project.service.creditHistory.CreditHistoryService;
import com.example.credit_service_project.service.utils.DelayUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DelayService {
    private final CreditHistoryService service;
    private final DelayRepository repository;
    private final DelayUtil util;

    /**
     * Add new Delay
     * @param fine BigDecimal
     * @param account Account
     * @return DelayResponse
     */
    public DelayResponse addNewDelay(BigDecimal fine, Account account) {
        Delay delay = util.createEntity(fine);
        delay.setCreditHistory(account.getCreditHistory());

        Delay saved = repository.save(delay);
        return util.convertToResponse(saved);
    }

    /**
     * In this method will be found all Delays belongs to CreditHistory
     * @param creditHistoryID UUID
     * @return List<DelayResponse>
     */
    @Transactional(readOnly = true)
    public List<DelayResponse> findAllDelaysBelongsToCreditHistory(UUID creditHistoryID) {
        CreditHistory creditHistory = service.findByIDForService(creditHistoryID);
        return repository.findAllByCreditHistory(creditHistory).stream()
                .map(util::convertToResponse)
                .collect(Collectors.toList());
    }
}

package com.example.credit_service_project.service.creditHistory;

import com.example.credit_service_project.dto.creditHistoryDTO.CreditHistoryResponse;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.CreditHistory;
import com.example.credit_service_project.repository.CreditHistoryRepository;
import com.example.credit_service_project.service.utils.CreditHistoryUtil;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditHistoryService {

    private final CreditHistoryRepository repository;
    private final CreditHistoryUtil util;

    /**
     * Method upon Account will create CreditHistory belongs this Account
     * @param account Account
     * @return CreditHistoryResponse
     */
    public CreditHistoryResponse createHistory(Account account) {
        CreditHistory creditHistory = util.createHistoryFromAccount(account);
        creditHistory.setDelays(new ArrayList<>());
        CreditHistory saved = repository.save(creditHistory);
        log.info("Create credit history for account: {}", account.getAccountNumber());
        return util.convertEntityToResponse(saved);
    }

    /**
     * Here will be found CreditHistory by ID and convert to response
     * @param id UUID
     * @return CreditHistoryResponse
     */
    @Transactional(readOnly = true)
    public CreditHistoryResponse findByID(UUID id) {
        CreditHistory history = findByIDForService(id);
        log.info("Find credit history with status: {}", history.getStatus());
        return util.convertEntityToResponse(history);
    }

    /**
     * Find CreditHistory by ID for another services
     * @param id UUID
     * @return CreditHistory
     */
    @Transactional(readOnly = true)
    public CreditHistory findByIDForService(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_CREDIT_HISTORY_MESSAGE));
    }

    /**
     * Find all CreditHistories
     * @return List<CreditHistoryResponse>
     */
    @Transactional(readOnly = true)
    public List<CreditHistoryResponse> findAllHistories() {
        log.info("Get all credit histories");
        return repository.findAll().stream()
                .map(util::convertEntityToResponse)
                .collect(Collectors.toList());
    }

}

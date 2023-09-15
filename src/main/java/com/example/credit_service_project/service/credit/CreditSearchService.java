package com.example.credit_service_project.service.credit;

import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Credit;
import com.example.credit_service_project.entity.enums.CreditStatus;
import com.example.credit_service_project.repository.CreditRepository;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditSearchService {

    private final CreditRepository repository;

    public Credit searchCreditByAccountAndStatus(Account account, CreditStatus status) {
        return repository.findByAccountAndCreditStatus(account, status).stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_CREDIT_MESSAGE));
    }

}

package com.example.credit_service_project.service.account;

import com.example.credit_service_project.DTO.accountDTO.AccountResponseDTO;
import com.example.credit_service_project.repository.AccountRepository;
import com.example.credit_service_project.service.utils.AccountUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GetAccountsListServiceImp  {

    private final AccountRepository repository;
    private final AccountUtil util;
    public List<AccountResponseDTO> execute() {
        var list = repository.findAll();
        return list.stream()
                .map(account -> util.convertAccountDTOResponse(account))
                .toList();
    }
}

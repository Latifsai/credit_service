package com.example.credit_service_project.service.account;

import com.example.credit_service_project.DTO.accountDTO.AddAccountDTORequest;
import com.example.credit_service_project.DTO.accountDTO.AddAccountDTOResponse;
import com.example.credit_service_project.repository.AccountRepository;
import com.example.credit_service_project.service.AccountService;
import com.example.credit_service_project.service.utils.AccountUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Validated
public class CreateAccountServiceImp implements AccountService<AddAccountDTOResponse, AddAccountDTORequest> {
    private final AccountRepository repository;
    private final AccountUtil util;
    @Override
    public AddAccountDTOResponse execute(@Valid AddAccountDTORequest request) {
        var account = util.convertAddRequestToAccount(request);
        repository.save(account);
        return util.convertAccountToAddResponse(account);
    }
}

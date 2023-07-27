package com.example.credit_service_project.service.account;

import com.example.credit_service_project.DTO.accountDTO.GetAccountsListResponse;
import com.example.credit_service_project.DTO.accountDTO.GetAccountsListRequest;
import com.example.credit_service_project.repository.AccountRepository;
import com.example.credit_service_project.service.AccountService;
import com.example.credit_service_project.service.exeption.ErrorsMessage;
import com.example.credit_service_project.service.exeption.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAccountsListServiceImp implements AccountService<GetAccountsListResponse, GetAccountsListRequest> {

    private final AccountRepository repository;

    @Override
    public GetAccountsListResponse execute(GetAccountsListRequest request) {
        var list = repository.findAllByStatus(request.getStatus());
        if (list.isEmpty()) {
            throw new NotFoundException(ErrorsMessage.NOT_FOUND_ACCOUNTSlIST_MESSAGE);
        }
        return new GetAccountsListResponse(list);
    }
}

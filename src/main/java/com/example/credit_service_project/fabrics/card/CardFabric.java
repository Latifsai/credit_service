package com.example.credit_service_project.fabrics.card;

import com.example.credit_service_project.DTO.cardDTO.AddCardDTORequest;
import com.example.credit_service_project.DTO.cardDTO.CardDTOResponse;
import com.example.credit_service_project.DTO.cardDTO.UpdateCardDTORequest;
import com.example.credit_service_project.repository.CardRepository;
import com.example.credit_service_project.service.CardService;
import com.example.credit_service_project.service.account.SearchAccountsServiceImp;
import com.example.credit_service_project.service.account.UpdateAccountServiceImp;
import com.example.credit_service_project.service.card.CreateCardServiceImp;
import com.example.credit_service_project.service.card.GetCardsServiceImp;
import com.example.credit_service_project.service.card.SearchCardServiceImp;
import com.example.credit_service_project.service.card.UpdateCardServiceImp;
import com.example.credit_service_project.service.utils.CardUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CardFabric implements CardAbstractFabric {

    private final CardRepository repository;
    private final SearchAccountsServiceImp searchAccountsService;
    private final UpdateAccountServiceImp updateAccountService;
    private final SearchCardServiceImp searchCardService;
    private final CreateCardServiceImp createCardServiceImp;
    private final CardUtil util;

    @Override
    public CardService<CardDTOResponse, AddCardDTORequest> activateCreateCard() {
        return new CreateCardServiceImp(repository, searchAccountsService, util);
    }

    @Override
    public GetCardsServiceImp getCardsService() {
        return new GetCardsServiceImp(repository, util);
    }


    @Override
    public CardService<CardDTOResponse, UpdateCardDTORequest> activateUpdateCard() {
        return new UpdateCardServiceImp(searchCardService, createCardServiceImp, searchAccountsService, updateAccountService, util);
    }

    @Override
    public CardService<CardDTOResponse, UUID> activateSearchCard() {
        return new SearchCardServiceImp(repository, util);
    }
}

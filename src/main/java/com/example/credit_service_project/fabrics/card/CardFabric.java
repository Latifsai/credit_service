package com.example.credit_service_project.fabrics.card;

import com.example.credit_service_project.DTO.cardDTO.*;
import com.example.credit_service_project.repository.AccountRepository;
import com.example.credit_service_project.repository.CardRepository;
import com.example.credit_service_project.service.CardService;
import com.example.credit_service_project.service.card.*;
import com.example.credit_service_project.service.utils.CardUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CardFabric implements CardAbstractFabric{

    private final CardRepository repository;
    private final AccountRepository accRepository;
    private final CardUtils util;
    @Override
    public CardService<AddedAndSearchCardDTOResponse, AddCardDTORequest> activateCreateCard() {
        return new CreateCardServiceImp(repository, accRepository, util);
    }

    @Override
    public CardService<DeleteCardDTOResponse, DeleteCardDTORequest> activateDeleteCard() {
        return new DeleteCardServiceImp(repository);
    }

    @Override
    public CardService<List<GetCardsResponse>, GetCardsRequest> activateGetCard() {
        return new GetCardsServiceImp(repository, util);
    }

    @Override
    public CardService<UpdateCardDTOResponse, UpdateCardDTORequest> activateUpdateCard() {
        return new UpdateCardServiceImp(repository, util);
    }

    @Override
    public CardService<AddedAndSearchCardDTOResponse, SearchCardDTOCreditRequest> activateSearchCard() {
        return new SearchCardServiceImp(repository, util);
    }
}

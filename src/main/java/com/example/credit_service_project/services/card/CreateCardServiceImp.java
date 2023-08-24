package com.example.credit_service_project.services.card;

import com.example.credit_service_project.DTO.cardDTO.AddCardDTORequest;
import com.example.credit_service_project.DTO.cardDTO.CardDTOResponse;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.repository.CardRepository;
import com.example.credit_service_project.services.CardService;
import com.example.credit_service_project.services.account.SearchAccountsServiceImp;
import com.example.credit_service_project.services.utils.CardUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateCardServiceImp implements CardService<CardDTOResponse, AddCardDTORequest> {

    private final CardRepository repository;
    private final SearchAccountsServiceImp searchAccountsService;
    private final CardUtil util;

    @Override
    public CardDTOResponse execute(AddCardDTORequest request) {
        Account account = searchAccountsService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber());
        Card card = util.convertAddRequestToEntity(request, account);
        Card savedCard = saveCard(card);
        return util.convertCardToAddDTOResponse(savedCard);
    }

    public Card saveCard(Card card) {
        return repository.save(card);
    }
}

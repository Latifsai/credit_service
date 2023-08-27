package com.example.credit_service_project.services.card;

import com.example.credit_service_project.DTO.cardDTO.CardDTOResponse;
import com.example.credit_service_project.DTO.cardDTO.UpdateCardDTORequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.services.account.AccountSearchService;
import com.example.credit_service_project.services.account.AccountUpdateService;
import com.example.credit_service_project.services.utils.CardUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardUpdateService {

    private final CardSearchService searchCardService;
    private final CardCreateService cardCreateService;
    private final AccountSearchService accountSearchService;
    private final AccountUpdateService updateAccountService;
    private final CardUtil utils;

    public CardDTOResponse updateCard(UpdateCardDTORequest request) {
        Card card = searchCardService.findCardById(request.getId());
        Account account = accountSearchService.findAccountByIdOrNumber(request.getAccountID(), request.getAccountNumber());

        Card updatedCard = utils.updateCard(card, request);
        updateCard(updatedCard);

        Account updatedAccount = utils.updateAccountBalance(account, card);
        updateAccountService.saveUpdatedAccount(updatedAccount);

        log.info("Update card: {}", card);
        return utils.convertCardToAddDTOResponse(updatedCard);
    }

    public void updateCard(Card updatedCard) {
        cardCreateService.saveCard(updatedCard);
    }
}

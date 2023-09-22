package com.example.credit_service_project.service.card;

import com.example.credit_service_project.dto.cardDTO.CardResponseDTO;
import com.example.credit_service_project.dto.cardDTO.UpdateCardRequest;
import com.example.credit_service_project.entity.Account;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.service.account.AccountUpdateService;
import com.example.credit_service_project.service.utils.CardUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardUpdateService {

    private final CardSearchService searchCardService;
    private final CardCreateService cardCreateService;
    private final AccountUpdateService updateAccountService;
    private final CardUtil utils;

    /**
     * Method will find a Card by ID an if Card present it will update
     * @param request UpdateCardRequest
     * @return CardResponseDTO
     */
    public CardResponseDTO saveUpdateCard(UpdateCardRequest request) {
        Card card = searchCardService.findCardById(request.getId());
        Account account = card.getAccount();

        Card updatedCard = utils.updateCard(card, request);
        saveUpdateCard(updatedCard);

        Account updatedAccount = utils.updateAccountBalance(account, card);
        updateAccountService.saveUpdatedAccount(updatedAccount);

        log.info("Update card with IBAN: {}", card.getIBAN());
        return utils.convertCardToAddDTOResponse(updatedCard);
    }

    /**
     * Save updated Card
     * @param updatedCard Card
     */
    public void saveUpdateCard(Card updatedCard) {
        cardCreateService.saveCard(updatedCard);
    }
}

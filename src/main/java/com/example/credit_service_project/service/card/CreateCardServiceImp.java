package com.example.credit_service_project.service.card;

import com.example.credit_service_project.DTO.cardDTO.AddCardDTORequest;
import com.example.credit_service_project.DTO.cardDTO.AddedAndSearchCardDTOResponse;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.repository.AccountRepository;
import com.example.credit_service_project.repository.CardRepository;
import com.example.credit_service_project.service.CardService;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.NotFoundException;
import com.example.credit_service_project.service.utils.CardUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateCardServiceImp implements CardService<AddedAndSearchCardDTOResponse, AddCardDTORequest> {

    private final CardRepository repository;
    private final AccountRepository accountRepository;
    private final CardUtils util;
    @Override
    public AddedAndSearchCardDTOResponse execute(AddCardDTORequest request) {
        var account = accountRepository.findByIdOrAccountNumber(request.getAccountId(), request.getAccountNumber());
        if (account.isPresent()) {
            Card card = util.convertAddRequestToEntity(request, account.get());
            repository.save(card);
            return util.convertCardToAddResponse(card);
        }
        throw new NotFoundException(ErrorsMessage.UNABLE_TO_ADD_CARD_MESSAGE);
    }
}

package com.example.credit_service_project.service.card;

import com.example.credit_service_project.DTO.cardDTO.AddedAndSearchCardDTOResponse;
import com.example.credit_service_project.DTO.cardDTO.SearchCardDTOCreditRequest;
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
public class SearchCardServiceImp implements CardService<AddedAndSearchCardDTOResponse, SearchCardDTOCreditRequest> {

    private final CardRepository repository;
    private final CardUtils utils;
    @Override
    public AddedAndSearchCardDTOResponse execute(SearchCardDTOCreditRequest request) {
        var card = repository.findByCardNumberAndHolderName(request.getCardNumber(), request.getHolderName());
        return card.map(c -> utils.convertCardToAddResponse(c))
                .orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_CARD_MESSAGE));
    }
}

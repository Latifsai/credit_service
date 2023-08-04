package com.example.credit_service_project.service.card;

import com.example.credit_service_project.DTO.cardDTO.CardDTOResponse;
import com.example.credit_service_project.DTO.cardDTO.UpdateCardDTORequest;
import com.example.credit_service_project.entity.Card;
import com.example.credit_service_project.repository.CardRepository;
import com.example.credit_service_project.service.CardService;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.NotFoundException;
import com.example.credit_service_project.service.utils.CardUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateCardServiceImp implements CardService<CardDTOResponse, UpdateCardDTORequest> {

    private final CardRepository repository;
    private final CardUtil utils;

    @Override
    public CardDTOResponse execute(UpdateCardDTORequest request) {
        var card = repository.findById(request.getId());

        if (card.isEmpty()) throw new NotFoundException(ErrorsMessage.NOT_FOUND_CARD_MESSAGE);

        var updatedCard = utils.updateCard(card.get(), request);
        updateCard(updatedCard);
        return utils.convertCardToAddDTOResponse(updatedCard);
    }

    public void updateCard(Card updatedCard) {
        repository.save(updatedCard);
    }
}

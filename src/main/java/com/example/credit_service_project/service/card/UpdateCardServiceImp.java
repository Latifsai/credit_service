package com.example.credit_service_project.service.card;

import com.example.credit_service_project.DTO.cardDTO.UpdateCardDTORequest;
import com.example.credit_service_project.DTO.cardDTO.UpdateCardDTOResponse;
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
public class UpdateCardServiceImp implements CardService<UpdateCardDTOResponse, UpdateCardDTORequest> {

    private final CardRepository repository;
    private final CardUtils utils;

    @Override
    public UpdateCardDTOResponse execute(UpdateCardDTORequest request) {
        var card = repository.findById(request.getId());

        if (card.isEmpty()) throw new NotFoundException(ErrorsMessage.NOT_FOUND_CARD_MESSAGE);

        var updatedCard = utils.updateCard(card.get(), request);
        repository.save(updatedCard);
        return utils.convertCardToUpdateResponse(updatedCard);
    }
}

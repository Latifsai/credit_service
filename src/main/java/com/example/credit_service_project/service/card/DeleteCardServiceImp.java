package com.example.credit_service_project.service.card;

import com.example.credit_service_project.DTO.cardDTO.DeleteCardDTORequest;
import com.example.credit_service_project.DTO.cardDTO.DeleteCardDTOResponse;
import com.example.credit_service_project.repository.CardRepository;
import com.example.credit_service_project.service.CardService;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteCardServiceImp implements CardService<DeleteCardDTOResponse, DeleteCardDTORequest> {

    private final CardRepository repository;

    @Override
    public DeleteCardDTOResponse execute(DeleteCardDTORequest request) {
        var card = repository.findByIdAndCardNumber(request.getId(),
                request.getCardNumber());

        return card.map(c -> {
            var response = new DeleteCardDTOResponse(c.getId(),
                    c.getCardNumber(), c.getAccount().getAccountNumber());

            repository.delete(c);
            return response;
        }).orElseThrow(()
                -> new NotFoundException(ErrorsMessage.NOT_FOUND_CARD_MESSAGE));
    }
}

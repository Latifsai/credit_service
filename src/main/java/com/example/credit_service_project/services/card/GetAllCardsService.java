package com.example.credit_service_project.services.card;

import com.example.credit_service_project.DTO.cardDTO.CardDTOResponse;
import com.example.credit_service_project.repositories.CardRepository;
import com.example.credit_service_project.services.utils.CardUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllCardsService {

    private final CardRepository repository;
    private final CardUtil util;

    @Transactional(readOnly = true)
    public List<CardDTOResponse> getAllCards() {
        log.info("Get a list of cards");
        return repository.findAll().stream()
                .map(util::convertCardToAddDTOResponse)
                .toList();
    }
}

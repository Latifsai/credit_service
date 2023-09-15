package com.example.credit_service_project.service.card;

import com.example.credit_service_project.dto.cardDTO.CardResponseDTO;
import com.example.credit_service_project.repository.CardRepository;
import com.example.credit_service_project.service.utils.CardUtil;
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
    public List<CardResponseDTO> getAllCards() {
        log.info("Get a list of cards");
        return repository.findAll().stream()
                .map(util::convertCardToAddDTOResponse)
                .toList();
    }
}

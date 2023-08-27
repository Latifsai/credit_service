package com.example.credit_service_project.services.client;

import com.example.credit_service_project.DTO.client.ClientResponseDTO;
import com.example.credit_service_project.repositories.ClientRepository;
import com.example.credit_service_project.services.utils.ClientUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllClientsService {

    private final ClientRepository repository;
    private final ClientUtil util;

    @Transactional(readOnly = true)
    public List<ClientResponseDTO> getAllClients() {
        log.info("Get a list of clients");
        return repository.findAll().stream()
                .map(util::convertClientToResponse)
                .toList();
    }
}

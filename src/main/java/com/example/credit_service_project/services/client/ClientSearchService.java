package com.example.credit_service_project.services.client;

import com.example.credit_service_project.DTO.client.ClientResponseDTO;
import com.example.credit_service_project.entity.Client;
import com.example.credit_service_project.repository.ClientRepository;
import com.example.credit_service_project.services.utils.ClientUtil;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientSearchService {

    private final ClientRepository repository;
    private final ClientUtil util;

    @Transactional(readOnly = true)
    public ClientResponseDTO searchClient(UUID id) {
        Client client = findClientById(id);
        return util.convertClientToResponse(client);
    }

    public Client findClientById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_CLIENT_MESSAGE));
    }

}

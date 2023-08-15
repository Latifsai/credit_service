package com.example.credit_service_project.service.client;

import com.example.credit_service_project.DTO.client.ClientResponseDTO;
import com.example.credit_service_project.entity.Client;
import com.example.credit_service_project.repository.ClientRepository;
import com.example.credit_service_project.service.ClientService;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.ClientNotFoundException;
import com.example.credit_service_project.service.utils.ClientUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class SearchClientServiceImp implements ClientService<ClientResponseDTO, UUID> {

    private final ClientRepository repository;
    private final ClientUtil util;

    @Override
    public ClientResponseDTO execute(UUID id) {
        Optional<Client> clientOptional = findClientById(id);
        return clientOptional.map(client -> util.convertClientToResponse(client))
                .orElseThrow(() -> new ClientNotFoundException(ErrorsMessage.NOT_FOUND_CLIENT_MESSAGE));
    }

    public Optional<Client> findClientById(UUID id) {
        return repository.findById(id);
    }

}

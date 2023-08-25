package com.example.credit_service_project.services.client;

import com.example.credit_service_project.DTO.client.ClientResponseDTO;
import com.example.credit_service_project.repository.ClientRepository;
import com.example.credit_service_project.services.utils.ClientUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllClientsService {

    private final ClientRepository repository;
    private final ClientUtil util;

    @Transactional(readOnly = true)
    public List<ClientResponseDTO> getAllClients() {
        return repository.findAll().stream()
                .map(util::convertClientToResponse)
                .toList();
    }
}

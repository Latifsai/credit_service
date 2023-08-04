package com.example.credit_service_project.service.client;

import com.example.credit_service_project.DTO.client.ClientResponseDTO;
import com.example.credit_service_project.repository.ClientRepository;
import com.example.credit_service_project.service.utils.ClientUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GetAllClientsService {

    private final ClientRepository repository;
    private final ClientUtil util;

    public List<ClientResponseDTO> execute() {
        var list = repository.findAll();
        return list.stream()
                .map(client -> util.convertClientToResponse(client))
                .toList();
    }
}

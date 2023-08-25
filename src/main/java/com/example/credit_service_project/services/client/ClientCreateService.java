package com.example.credit_service_project.services.client;

import com.example.credit_service_project.DTO.client.AddClientRequest;
import com.example.credit_service_project.DTO.client.ClientResponseDTO;
import com.example.credit_service_project.entity.Client;
import com.example.credit_service_project.entity.Manager;
import com.example.credit_service_project.repository.ClientRepository;
import com.example.credit_service_project.services.manager.ManagerSearchService;
import com.example.credit_service_project.services.utils.ClientUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientCreateService {

    private final ClientRepository repository;
    private final ManagerSearchService searchManagerService;
    private final ClientUtil util;

    public ClientResponseDTO createClient(AddClientRequest request) {
        Manager manager = searchManagerService.findManagerById(request.getManagerID());
        Client client = util.convertAddRequestToEntity(request, manager);
        Client savedClient = saveClient(client);
        return util.convertClientToResponse(savedClient);

    }

    public Client saveClient(Client client) {
        return repository.save(client);
    }

}

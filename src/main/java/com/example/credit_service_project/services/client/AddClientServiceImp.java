package com.example.credit_service_project.services.client;

import com.example.credit_service_project.DTO.client.AddClientRequest;
import com.example.credit_service_project.DTO.client.ClientResponseDTO;
import com.example.credit_service_project.entity.Client;
import com.example.credit_service_project.entity.Manager;
import com.example.credit_service_project.repository.ClientRepository;
import com.example.credit_service_project.services.ClientService;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.services.manager.SearchManagerServiceImp;
import com.example.credit_service_project.services.utils.ClientUtil;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AddClientServiceImp implements ClientService<ClientResponseDTO, AddClientRequest> {

    private final ClientRepository repository;
    private final SearchManagerServiceImp searchManagerService;
    private final ClientUtil util;

    @Override
    public ClientResponseDTO execute(AddClientRequest request) {
        Manager manager = searchManagerService.findManagerById(request.getManagerID())
                .orElseThrow(() -> new NotFoundException(ErrorsMessage.UNABLE_TO_ADD_CLIENT_MESSAGE));

        Client client = util.convertAddRequestToEntity(request, manager);
        Client savedClient = saveClient(client);
        return util.convertClientToResponse(savedClient);

    }

    public Client saveClient(Client client) {
        return repository.save(client);
    }

}

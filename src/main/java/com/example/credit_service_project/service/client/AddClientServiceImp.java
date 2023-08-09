package com.example.credit_service_project.service.client;

import com.example.credit_service_project.DTO.client.AddClientRequest;
import com.example.credit_service_project.DTO.client.ClientResponseDTO;
import com.example.credit_service_project.entity.Client;
import com.example.credit_service_project.repository.ClientRepository;
import com.example.credit_service_project.service.ClientService;
import com.example.credit_service_project.service.errors.ErrorsMessage;
import com.example.credit_service_project.service.errors.exceptions.ClientNotFoundException;
import com.example.credit_service_project.service.errors.exceptions.ManagerNotFoundException;
import com.example.credit_service_project.service.manager.SearchManagerServiceImp;
import com.example.credit_service_project.service.utils.ClientUtil;
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
        var manager = searchManagerService.findManagerById(request.getManagerID());
        if (manager.isPresent()){
            Client client = util.convertAddRequestToEntity(request, manager.get());
            saveClient(client);
            return util.convertClientToResponse(client);
        }
        throw new ClientNotFoundException(ErrorsMessage.UNABLE_TO_ADD_CLIENT_MESSAGE);
    }

    public Client saveClient(Client client) {
        return repository.save(client);
    }

}

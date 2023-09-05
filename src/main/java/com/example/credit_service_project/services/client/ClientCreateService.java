package com.example.credit_service_project.services.client;

import com.example.credit_service_project.DTO.client.AddClientRequest;
import com.example.credit_service_project.DTO.client.ClientResponseDTO;
import com.example.credit_service_project.entity.Client;
import com.example.credit_service_project.entity.Manager;
import com.example.credit_service_project.repositories.ClientRepository;
import com.example.credit_service_project.repositories.RoleRepository;
import com.example.credit_service_project.services.manager.ManagerSearchService;
import com.example.credit_service_project.services.utils.ClientUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientCreateService implements UserDetailsService {
    private final RoleRepository roleRepository;
    private final ClientRepository repository;
    private final ManagerSearchService searchManagerService;
    private final ClientUtil util;

    public ClientResponseDTO createClient(AddClientRequest request) {
        Manager manager = searchManagerService.findManagerById(request.getManagerID());
        Client client = util.convertAddRequestToEntity(request, manager);
        Client savedClient = saveClient(client);
        log.info("Create and save client with ID: {}", savedClient.getId());
        return util.convertClientToResponse(savedClient);
    }

    public Client saveClient(Client client) {
        return repository.save(client);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}

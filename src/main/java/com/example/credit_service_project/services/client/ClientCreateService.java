package com.example.credit_service_project.services.client;

import com.example.credit_service_project.DTO.client.AddClientRequest;
import com.example.credit_service_project.DTO.client.ClientResponseDTO;
import com.example.credit_service_project.entity.Client;
import com.example.credit_service_project.entity.Manager;
import com.example.credit_service_project.repositories.ClientRepository;
import com.example.credit_service_project.repositories.RoleRepository;
import com.example.credit_service_project.services.manager.ManagerSearchService;
import com.example.credit_service_project.services.utils.ClientUtil;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

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

        client.setRole(roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_ROLE_MESSAGE)));

        Client savedClient = saveClient(client);
        log.info("Create and save client with ID: {}", savedClient.getId());
        return util.convertClientToResponse(savedClient);
    }

    public Client saveClient(Client client) {
        return repository.save(client);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = repository.findByName(username)
                .orElseThrow(() -> new  UsernameNotFoundException(ErrorsMessage.NOT_FOUND_CLIENT_MESSAGE + " with name: " + username));
        return new User(
                client.getName(),
                client.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(client.getRole().getName()))
        );
    }


}

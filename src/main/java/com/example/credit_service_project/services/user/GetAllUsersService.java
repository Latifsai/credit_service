package com.example.credit_service_project.services.user;

import com.example.credit_service_project.DTO.user.UserResponseDTO;
import com.example.credit_service_project.repositories.UserRepository;
import com.example.credit_service_project.services.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllUsersService {

    private final UserRepository repository;
    private final UserUtil util;

    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllClients() {
        log.info("Get a list of users");
        return repository.findAll().stream()
                .map(util::convertUserToResponse)
                .toList();
    }
}

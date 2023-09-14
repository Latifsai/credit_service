package com.example.credit_service_project.services.user;

import com.example.credit_service_project.dto.user.UserResponseDTO;
import com.example.credit_service_project.entity.User;
import com.example.credit_service_project.repositories.UserRepository;
import com.example.credit_service_project.services.utils.UserUtil;
import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserSearchService {

    private final UserRepository repository;
    private final UserUtil util;

    @Transactional(readOnly = true)
    public UserResponseDTO searchUser(UUID id) {
        User user = findUserById(id);
        log.info("Search client with ID: {}", user.getId());
        return util.convertUserToResponse(user);
    }

    public User findUserById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorsMessage.NOT_FOUND_USER_MESSAGE));
    }

}

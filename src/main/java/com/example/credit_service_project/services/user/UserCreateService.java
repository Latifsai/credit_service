package com.example.credit_service_project.services.user;

import com.example.credit_service_project.DTO.user.CreateUserRequest;
import com.example.credit_service_project.DTO.user.UserResponseDTO;
import com.example.credit_service_project.entity.User;
import com.example.credit_service_project.repositories.UserRepository;
import com.example.credit_service_project.services.role.RoleService;
import com.example.credit_service_project.services.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * In this service will be created new Users.
 * The service take create request and return data about saved User.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserCreateService {
    private final RoleService roleService;
    private final UserRepository repository;
    private final UserUtil util;

    public UserResponseDTO createClient(CreateUserRequest request) {
        User user = util.convertAddRequestToEntity(request);
        user.setRole(roleService.findByRoleName(request.getName().toUpperCase()));
        User savedUser = saveClient(user);
        log.info("Create and save client with ID: {}", savedUser.getId());
        return util.convertClientToResponse(savedUser);
    }

    public User saveClient(User user) {
        return repository.save(user);
    }




}

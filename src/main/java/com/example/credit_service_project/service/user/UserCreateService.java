package com.example.credit_service_project.service.user;

import com.example.credit_service_project.dto.user.CreateUserRequest;
import com.example.credit_service_project.dto.user.UserResponseDTO;
import com.example.credit_service_project.entity.User;
import com.example.credit_service_project.repository.UserRepository;
import com.example.credit_service_project.service.role.RoleService;
import com.example.credit_service_project.service.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO createClient(CreateUserRequest request) {
        User user = util.convertAddRequestToEntity(request);
        user.setRole(roleService.findByRoleName(request.getRole().toUpperCase()));
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User savedUser = saveClient(user);
        log.info("Create and save client with ID: {}", savedUser.getId());
        return util.convertUserToResponse(savedUser);
    }

    public User saveClient(User user) {
        return repository.save(user);
    }
}

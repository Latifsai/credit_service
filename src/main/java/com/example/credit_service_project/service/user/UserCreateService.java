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

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCreateService {
    private final RoleService roleService;
    private final UserRepository repository;
    private final UserUtil util;
    private final PasswordEncoder passwordEncoder;

    /**
     * Create new User upon request
     * @param request CreateUserRequest
     * @return UserResponseDTO
     */
    public UserResponseDTO createUser(CreateUserRequest request) {
        User user = util.convertAddRequestToEntity(request);
        user.setRole(roleService.findByRoleName(request.getRole().toUpperCase()));
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User savedUser = saveUser(user);
        log.info("Create and save client with ID: {}", savedUser.getId());
        return util.convertUserToResponse(savedUser);
    }

    /**
     * Save User in DB
     * @param user User
     * @return User
     */
    public User saveUser(User user) {
        return repository.save(user);
    }
}

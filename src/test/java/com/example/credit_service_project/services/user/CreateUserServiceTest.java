package com.example.credit_service_project.services.user;

import com.example.credit_service_project.dto.user.CreateUserRequest;
import com.example.credit_service_project.entity.User;
import com.example.credit_service_project.repositories.UserRepository;
import com.example.credit_service_project.generators.UserDTOGenerator;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.services.role.RoleService;
import com.example.credit_service_project.services.utils.UserUtil;
import jakarta.validation.Validation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateUserServiceTest {
    @Mock
    private UserRepository repository;
    @Mock
    private UserUtil util;
    @Mock
    private RoleService roleService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserCreateService service;


    @Test
    public void testCreateUserService() {
        CreateUserRequest request = new CreateUserRequest("Aziz", "Snow", BigDecimal.valueOf(2500),
                BigDecimal.ZERO, new BigDecimal("1500"), "Johan's Str 34", "john_manager@loewen.de",
                "+49 176 28835002", "MANAGER", "2125");

        User user = EntityCreator.getUser();

        when(util.convertAddRequestToEntity(request)).thenReturn(user);
        when(roleService.findByRoleName("MANAGER")).thenReturn(EntityCreator.getManagerRole());
        when(util.convertUserToResponse(user)).thenReturn(UserDTOGenerator.getResponse());
        when(passwordEncoder.encode(request.getPassword())).thenReturn("$2a$10$ruSCjhXF8i30nqNtvAeS0OSyzfF3vOR3Oiq6keZN.XLRgRw9ZvPCC");
        when(repository.save(user)).thenReturn(user);

        assertEquals(UserDTOGenerator.getResponse(), service.createClient(request));
    }

    @Test
    public void testAddClientValidation() {
        User user = EntityCreator.getUserForValidation();
        var validation = Validation.buildDefaultValidatorFactory().getValidator();
        var set = validation.validate(user);
        assertEquals(3, set.size());
    }

}
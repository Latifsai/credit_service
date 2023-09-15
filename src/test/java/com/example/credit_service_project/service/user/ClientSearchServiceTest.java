package com.example.credit_service_project.service.user;

import com.example.credit_service_project.repository.UserRepository;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.service.utils.UserUtil;
import com.example.credit_service_project.generators.UserDTOGenerator;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientSearchServiceTest {
    @Mock
    private UserRepository repository;
    @Mock
    private UserUtil util;
    @InjectMocks
    private UserSearchService service;

    @Test
    public void testAddClientSuccess() {
        UUID id = UUID.randomUUID();
        var client = EntityCreator.getUser();

        when(repository.findById(id)).thenReturn(Optional.of(client));
        when(util.convertUserToResponse(client)).thenReturn(UserDTOGenerator.getResponse());

        assertEquals(UserDTOGenerator.getResponse(), service.searchUser(id));
    }

    @Test
    public void testAddClientNotFoundException() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.searchUser(id));
    }


}
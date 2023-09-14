package com.example.credit_service_project.services.user;

import com.example.credit_service_project.entity.User;
import com.example.credit_service_project.repositories.UserRepository;
import com.example.credit_service_project.generators.EntityCreator;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserDeleteServiceTest {
    @Mock
    private UserRepository repository;
    @Mock
    private UserSearchService searchService;
    @InjectMocks
    private UserDeleteService deleteService;

    @Test
    public void testDeleteUser() {
        User user = EntityCreator.getUser();
        when(searchService.findUserById(EntityCreator.getUser().getId())).thenReturn(user);

        deleteService.delete(user.getId());

        verify(repository, times(1)).delete(user);
        verify(searchService, times(1)).findUserById(user.getId());
    }

    @Test
    public void testDeleteUserNotFoundException() {
        UUID id = UUID.randomUUID();

        when(searchService.findUserById(id)).thenThrow(NotFoundException.class);
        Assertions.assertThrows(NotFoundException.class, () -> deleteService.delete(id));
    }
}
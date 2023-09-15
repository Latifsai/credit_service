package com.example.credit_service_project.service.user;

import com.example.credit_service_project.entity.User;
import com.example.credit_service_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserDeleteService {

    private final UserRepository repository;
    private final UserSearchService searchService;

    public void delete(UUID id) {
        User user = searchService.findUserById(id);
        repository.delete(user);
    }
}

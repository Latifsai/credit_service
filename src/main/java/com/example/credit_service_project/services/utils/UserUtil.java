package com.example.credit_service_project.services.utils;

import com.example.credit_service_project.DTO.user.CreateUserRequest;
import com.example.credit_service_project.DTO.user.UserResponseDTO;
import com.example.credit_service_project.DTO.user.UpdateClientRequest;
import com.example.credit_service_project.entity.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class UserUtil {

    public User convertAddRequestToEntity(CreateUserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setSalary(request.getSalary());
        user.setPassiveIncome(request.getPassiveIncome());
        user.setExpenses(request.getExpenses());
        user.setAddress(request.getAddress());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setRegistrationDate(LocalDate.now());
        user.setUpdateDate(LocalDate.now());
        return user;
    }

    public UserResponseDTO convertUserToResponse(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .income(user.getSalary().add(user.getPassiveIncome()))
                .expenses(user.getExpenses())
                .address(user.getAddress())
                .email(user.getEmail())
                .phone(user.getPhone())
                .registrationDate(user.getRegistrationDate())
                .updateDate(user.getUpdateDate())
                .build();
    }

    public User updateClient(User user, UpdateClientRequest request) {
        if (checkInput(request.getSalary())) user.setSalary(request.getSalary());
        if (checkInput(request.getPassiveIncome())) user.setPassiveIncome(request.getPassiveIncome());
        if (checkInput(request.getExpenses())) user.setExpenses(request.getExpenses());
        if (checkForStrings(request.getAddress())) user.setAddress(request.getAddress());
        if (checkForStrings(request.getEmail())) user.setEmail(request.getEmail());
        if (checkForStrings(request.getPhone())) user.setPhone(request.getPhone());
        return user;
    }

    private boolean checkInput(BigDecimal input) {
        return input != null;
    }

    private boolean checkForStrings(String criteria) {
        return criteria != null && !criteria.trim().isEmpty();
    }
}


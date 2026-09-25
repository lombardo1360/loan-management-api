package com.bank.loan.application.service;

import com.bank.loan.application.dto.CreateUserRequest;
import com.bank.loan.application.dto.UpdateUserRequest;
import com.bank.loan.application.dto.UserResponse;
import com.bank.loan.domain.exception.InvalidRoleException;
import com.bank.loan.domain.exception.UserNotFoundException;
import com.bank.loan.domain.model.User;
import com.bank.loan.infrastructure.persistence.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse createUser(
            CreateUserRequest request
    ) {

        User user = new User(
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                request.getRole()
        );

        User savedUser = userRepository.save(user);

        return toResponse(savedUser);
    }

    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found with id: " + id
                        )
                );

        return toResponse(user);
    }

    public void deleteUser(Long id) {

        if (!userRepository.existsById(id)) {
            throw new RuntimeException(
                    "User not found with id: " + id
            );
        }

        userRepository.deleteById(id);
    }

    private UserResponse toResponse(User user) {

        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getRole()
        );
    }

    public UserResponse updateUser(
            Long id,
            UpdateUserRequest request
    ) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with id: " + id
                        )
                );

        if (!request.getRole().equals("ROLE_USER")
                && !request.getRole().equals("ROLE_ADMIN")) {

            throw new InvalidRoleException(
                    "Role must be ROLE_USER or ROLE_ADMIN"
            );
        }

        User updatedUser = new User(
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                request.getRole()
        );

        return toResponse(
                userRepository.save(updatedUser)
        );
    }
}
package com.bank.loan.application.service;

import com.bank.loan.domain.model.User;
import com.bank.loan.infrastructure.persistence.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(
            String username,
            String password,
            String role
    ) {

        User user = new User(
                username,
                passwordEncoder.encode(password),
                role
        );

        return userRepository.save(user);
    }
}
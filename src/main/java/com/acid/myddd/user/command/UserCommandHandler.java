package com.acid.myddd.user.command;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acid.myddd.user.domain.model.User;
import com.acid.myddd.user.domain.repository.UserRepository;
import com.acid.myddd.user.domain.service.UserDomainService;

@Service
@Transactional
public class UserCommandHandler {
    private final UserRepository userRepository;
    private final UserDomainService userDomainService;

    public UserCommandHandler(UserRepository userRepository, UserDomainService userDomainService) {
        this.userRepository = userRepository;
        this.userDomainService = userDomainService;
    }

    public User handle(CreateUserCommand command) {
        userDomainService.validateNewUser(command.getUsername(), command.getEmail());
        User user = new User(command.getUsername(), command.getEmail(), command.getPassword());
        return userRepository.save(user);
    }

    public User handle(UUID userId, UpdateEmailCommand command) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.updateEmail(command.getEmail());
        return userRepository.save(user);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
} 
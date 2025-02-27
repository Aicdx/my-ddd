package com.acid.myddd.user.application.command;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acid.myddd.user.domain.model.User;
import com.acid.myddd.user.domain.repository.UserRepository;

@Service
@Transactional
public class UserCommandService {
    private final UserRepository userRepository;

    public UserCommandService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String username, String email, String password) {
        User user = new User(username, email, password);
        return userRepository.save(user);
    }

    public User updateUserEmail(UUID id, String newEmail) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.updateEmail(newEmail);
        return userRepository.save(user);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
} 
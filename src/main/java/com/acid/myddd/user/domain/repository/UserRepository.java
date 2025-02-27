package com.acid.myddd.user.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.acid.myddd.user.domain.model.User;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(UUID id);
    Optional<User> findByUsername(String username);
    List<User> findAll();
    void deleteById(UUID id);
} 
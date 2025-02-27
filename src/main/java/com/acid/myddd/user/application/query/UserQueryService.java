package com.acid.myddd.user.application.query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.acid.myddd.user.interfaces.dto.UserDTO;

public interface UserQueryService {
    Optional<UserDTO> findById(UUID id);
    Optional<UserDTO> findByUsername(String username);
    List<UserDTO> findAll();
} 
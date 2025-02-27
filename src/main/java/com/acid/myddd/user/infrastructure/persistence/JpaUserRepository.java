package com.acid.myddd.user.infrastructure.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acid.myddd.user.domain.model.User;
import com.acid.myddd.user.domain.repository.UserRepository;

@Repository
public interface JpaUserRepository extends JpaRepository<User, UUID>, UserRepository {
    @Override
    Optional<User> findByUsername(String username);
} 
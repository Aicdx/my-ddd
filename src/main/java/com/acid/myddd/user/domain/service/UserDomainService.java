package com.acid.myddd.user.domain.service;

import org.springframework.stereotype.Service;

import com.acid.myddd.user.domain.repository.UserRepository;

@Service
public class UserDomainService {
    private final UserRepository userRepository;
    
    public UserDomainService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    // 检查用户名是否唯一
    public boolean isUsernameUnique(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }
    
    // 验证新用户信息
    public void validateNewUser(String username, String email) {
        if (!isUsernameUnique(username)) {
            throw new IllegalArgumentException("Username already exists");
        }
    }
} 
package com.acid.myddd.user.command;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.ApplicationEventPublisher;

import com.acid.myddd.user.domain.model.User;
import com.acid.myddd.user.domain.repository.UserRepository;
import com.acid.myddd.user.domain.service.UserDomainService;

@Service
@Transactional
public class UserCommandHandler {
    private final UserRepository userRepository;
    private final UserDomainService userDomainService;
    private final ApplicationEventPublisher eventPublisher;

    public UserCommandHandler(
            UserRepository userRepository, 
            UserDomainService userDomainService,
            ApplicationEventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.userDomainService = userDomainService;
        this.eventPublisher = eventPublisher;
    }

    // 处理创建用户命令
    public User handle(CreateUserCommand command) {
        // 验证新用户信息
        userDomainService.validateNewUser(command.getUsername(), command.getEmail());
        // 创建用户实体
        User user = new User(command.getUsername(), command.getEmail(), command.getPassword());
        return userRepository.save(user);
    }

    // 处理更新邮箱命令
    public User handle(UUID userId, UpdateEmailCommand command) {
        // 查找用户
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        // 更新邮箱
        user.updateEmail(command.getEmail());
        User savedUser = userRepository.save(user);
        
        // 发布领域事件
        user.getDomainEvents().forEach(eventPublisher::publishEvent);
        user.clearDomainEvents();
        
        return savedUser;
    }

    // 处理更新密码命令
    public User handle(UUID userId, UpdatePasswordCommand command) {
        // 查找用户
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        // 更新密码
        user.changePassword(command.getCurrentPassword(), command.getNewPassword());
        User savedUser = userRepository.save(user);
        
        // 发布领域事件
        user.getDomainEvents().forEach(eventPublisher::publishEvent);
        user.clearDomainEvents();
        
        return savedUser;
    }


    // 删除用户
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
} 
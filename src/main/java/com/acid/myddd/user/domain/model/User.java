package com.acid.myddd.user.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.acid.myddd.user.domain.event.UserEmailChangedEvent;
import com.acid.myddd.user.domain.event.UserEvent;
import com.acid.myddd.user.domain.event.UserPasswordChangedEvent;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
import lombok.Getter;

@Entity
@Table(name = "users")
@Getter
public class User {
    // 用户唯一标识
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    // 用户名，不允许重复
    @Column(unique = true)
    private String username;
    
    // 邮箱值对象
    @Embedded
    private Email email;
    
    // 用户密码
    @Column
    private String password;
    
    // 版本号，用于乐观锁控制
    @Version
    private Long version;
    
    // 领域事件集合，不持久化
    @Transient
    private List<UserEvent> domainEvents = new ArrayList<>();
    
    public User(String username, String email, String password) {
        this.username = username;
        this.email = new Email(email);
        this.password = password; // 实际项目中应该加密
    }
    
    protected User() {
        // JPA需要
    }
    
    public void changePassword(String currentPassword, String newPassword) {
        if (!this.password.equals(currentPassword)) {
            throw new IllegalArgumentException("Current password is incorrect");
        }
        if (newPassword == null || newPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("New password cannot be empty");
        }
        this.password = newPassword; // 实际项目中应该加密
        // 添加领域事件
        this.domainEvents.add(new UserPasswordChangedEvent(this.id, currentPassword, newPassword));
    }
    
    public void updateEmail(String newEmail) {
        String oldEmail = this.email.toString();
        this.email = new Email(newEmail);
        // 添加领域事件
        this.domainEvents.add(new UserEmailChangedEvent(this.id, oldEmail, newEmail));
    }
    
    public List<UserEvent> getDomainEvents() {
        return new ArrayList<>(domainEvents);
    }
    
    public void clearDomainEvents() {
        this.domainEvents.clear();
    }
} 
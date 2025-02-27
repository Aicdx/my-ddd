package com.acid.myddd.user.domain.model;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email {
    // 邮箱地址
    private String address;
    
    // 创建邮箱值对象，确保邮箱格式正确
    public Email(String address) {
        if (!isValid(address)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.address = address;
    }
    
    // 验证邮箱格式是否正确
    private boolean isValid(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
    
    @Override
    public String toString() {
        return address;
    }
} 
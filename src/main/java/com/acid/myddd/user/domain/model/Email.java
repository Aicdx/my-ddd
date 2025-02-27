package com.acid.myddd.user.domain.model;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email {
    private String address;
    
    public Email(String address) {
        if (!isValid(address)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.address = address;
    }
    
    private boolean isValid(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
    
    @Override
    public String toString() {
        return address;
    }
} 
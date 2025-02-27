package com.acid.myddd.user.domain.event;

import java.util.UUID;

import lombok.Getter;

@Getter
public class UserEmailChangedEvent extends UserEvent {
    private final String oldEmail;
    private final String newEmail;
    
    public UserEmailChangedEvent(UUID userId, String oldEmail, String newEmail) {
        super(userId);
        this.oldEmail = oldEmail;
        this.newEmail = newEmail;
    }
} 
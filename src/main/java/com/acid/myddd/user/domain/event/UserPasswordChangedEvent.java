package com.acid.myddd.user.domain.event;

import java.util.UUID;

import lombok.Getter;

@Getter
public class UserPasswordChangedEvent extends UserEvent {
    private final String currentPassword;
    private final String newPassword;

    public UserPasswordChangedEvent(UUID userId, String currentPassword, String newPassword) {
        super(userId);
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }
}

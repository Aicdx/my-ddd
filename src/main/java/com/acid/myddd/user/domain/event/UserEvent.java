package com.acid.myddd.user.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;

@Getter
public abstract class UserEvent {
    private final UUID userId;
    private final LocalDateTime occurredOn;
    
    protected UserEvent(UUID userId) {
        this.userId = userId;
        this.occurredOn = LocalDateTime.now();
    }
} 
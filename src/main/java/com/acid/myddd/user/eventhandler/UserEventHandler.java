package com.acid.myddd.user.eventhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.acid.myddd.user.domain.event.UserEmailChangedEvent;

@Component
public class UserEventHandler {
    private static final Logger logger = LoggerFactory.getLogger(UserEventHandler.class);
    
    @EventListener
    public void handleUserEmailChanged(UserEmailChangedEvent event) {
        logger.info("用户 {} 的邮箱从 {} 更改为 {}", 
            event.getUserId(), 
            event.getOldEmail(), 
            event.getNewEmail());
    }
} 
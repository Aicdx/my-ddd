package com.acid.myddd.user.eventhandler;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.acid.myddd.user.domain.event.UserEmailChangedEvent;

@Component
public class UserEventHandler {
    
    @EventListener
    public void handleUserEmailChanged(UserEmailChangedEvent event) {
        // 处理邮箱变更事件，例如发送通知邮件
        System.out.println("User " + event.getUserId() + " changed email from " + 
            event.getOldEmail() + " to " + event.getNewEmail());
    }
} 
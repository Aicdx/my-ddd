package com.acid.myddd.user.interfaces.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class UserDTO {
    private UUID id;
    private String username;
    private String email;
    
    // 不包含敏感信息如密码
} 
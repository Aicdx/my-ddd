package com.acid.myddd.user.query.dto;

import java.util.UUID;
import lombok.Data;

@Data
public class UserDTO {
    private UUID id;
    private String username;
    private String email;
} 
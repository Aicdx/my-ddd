package com.acid.myddd.user.interfaces.rest;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String username;
    private String email;
    private String password;
} 
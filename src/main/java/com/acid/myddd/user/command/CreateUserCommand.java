package com.acid.myddd.user.command;

import lombok.Data;

@Data
public class CreateUserCommand {
    private String username;
    private String email;
    private String password;
} 
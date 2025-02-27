package com.acid.myddd.user.command;

import lombok.Data;

@Data
public class UpdatePasswordCommand {
    private String currentPassword;
    private String newPassword;
}

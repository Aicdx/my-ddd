package com.acid.myddd.user.query.dto;

import lombok.Data;

@Data
public class UserQueryParam {
    private String username;
    private String email;
    private Integer pageNum = 1;
    private Integer pageSize = 10;
} 
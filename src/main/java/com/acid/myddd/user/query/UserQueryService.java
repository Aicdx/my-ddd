package com.acid.myddd.user.query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.acid.myddd.user.query.dto.UserDTO;
import com.acid.myddd.user.query.dto.UserQueryParam;
import com.github.pagehelper.PageInfo;

public interface UserQueryService {
    Optional<UserDTO> findById(UUID id);
    Optional<UserDTO> findByUsername(String username);
    List<UserDTO> findAll();
    PageInfo<UserDTO> findUsers(UserQueryParam param);
} 
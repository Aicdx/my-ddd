package com.acid.myddd.user.infrastructure.persistence.mapper;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.acid.myddd.user.query.dto.UserDTO;
import com.acid.myddd.user.query.dto.UserQueryParam;

@Mapper
public interface UserMapper {
    List<UserDTO> findUsers(UserQueryParam param);
    UserDTO findById(@Param("id") UUID id);
    UserDTO findByUsername(@Param("username") String username);
} 
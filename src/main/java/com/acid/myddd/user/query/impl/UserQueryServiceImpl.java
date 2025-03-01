package com.acid.myddd.user.query.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acid.myddd.user.infrastructure.persistence.mapper.UserMapper;
import com.acid.myddd.user.query.UserQueryService;
import com.acid.myddd.user.query.dto.UserDTO;
import com.acid.myddd.user.query.dto.UserQueryParam;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
@Transactional(readOnly = true)
public class UserQueryServiceImpl implements UserQueryService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Optional<UserDTO> findById(UUID id) {
        return Optional.ofNullable(userMapper.findById(id));
    }

    @Override
    public Optional<UserDTO> findByUsername(String username) {
        return Optional.ofNullable(userMapper.findByUsername(username));
    }

    @Override
    public List<UserDTO> findAll() {
        return userMapper.findUsers(new UserQueryParam());
    }

    @Override
    public PageInfo<UserDTO> findUsers(UserQueryParam param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<UserDTO> users = userMapper.findUsers(param);
        return new PageInfo<>(users);
    }
} 
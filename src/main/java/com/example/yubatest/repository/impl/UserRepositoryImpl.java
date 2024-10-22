package com.example.yubatest.repository.impl;

import com.example.yubatest.entity.domainobject.UserDo;
import com.example.yubatest.mapper.UserMapper;
import com.example.yubatest.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@AllArgsConstructor
public class UserRepositoryImpl extends BaseRepositoryImpl<UserMapper, UserDo> implements UserRepository {
    private final UserMapper userMapper;



}

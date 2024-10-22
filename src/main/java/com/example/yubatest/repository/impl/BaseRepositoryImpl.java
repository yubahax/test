package com.example.yubatest.repository.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yubatest.entity.common.BaseDo;
import com.example.yubatest.repository.BaseRepository;

import java.io.Serializable;
import java.util.Collection;

public class BaseRepositoryImpl<M extends BaseMapper<T>, T extends BaseDo> extends ServiceImpl<M, T> implements BaseRepository<T> {

}
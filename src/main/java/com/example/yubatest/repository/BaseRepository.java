package com.example.yubatest.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yubatest.entity.common.BaseDo;

import java.io.Serializable;
import java.util.Collection;
public interface BaseRepository<T extends BaseDo> extends IService<T> {
    boolean removeEnById(Serializable id);

    boolean removeBatchEnByIds(Collection<? extends Serializable> ids);
}

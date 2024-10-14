package com.example.yubatest.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.yubatest.entity.co.UserCo;
import com.example.yubatest.entity.dto.UserDo;
import com.example.yubatest.mapper.UserMapper;
import com.example.yubatest.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * user(User)表服务实现类
 *
 * @author makejava
 * @since 2024-10-11 09:54:46
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserDo queryById(Long id) {
        return this.userMapper.selectById(id);
    }

    /**
     * 分页查询
     *
     * @param user        筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page queryByPage(UserCo user, PageRequest pageRequest) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page page = new com.baomidou.mybatisplus.extension.plugins.pagination.Page((long)pageRequest.getPageNumber(), (long)pageRequest.getPageSize());
        return  this.userMapper.selectPage(page, new QueryWrapper<UserDo>());
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public UserDo insert(UserCo user) {
        this.userMapper.insert(new UserDo());
        return new UserDo();
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public UserDo update(UserCo user) {
        UserDo userDo = BeanUtil.copyProperties(user, UserDo.class);
        this.userMapper.update(userDo,new QueryWrapper<>());
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.userMapper.deleteById(id) > 0;
    }
}

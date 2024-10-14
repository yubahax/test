package com.example.yubatest.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.yubatest.entity.co.UserCo;
import com.example.yubatest.entity.dto.UserDo;
import org.springframework.data.domain.PageRequest;

/**
 * user(User)表服务接口
 *
 * @author makejava
 * @since 2024-10-11 09:54:46
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserDo queryById(Long id);

    /**
     * 分页查询
     *
     * @param user        筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page queryByPage(UserCo user, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    UserDo insert(UserCo user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    UserDo update(UserCo user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}

package com.example.yubatest.mapper;

import com.example.yubatest.entity.dto.UserDo;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * user(User)表数据库访问层
 *
 * @author makejava
 * @since 2024-10-14 11:30:41
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDo> {

}


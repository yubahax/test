package com.example.yubatest.entity.page;


import java.util.Date;
import java.io.Serializable;

import com.example.yubatest.entity.common.PageQuery;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * user(User)实体类
 *
 * @author makejava
 * @since 2024-10-14 11:30:42
 */

@Data
public class UserPageQry extends PageQuery {
    private static final long serialVersionUID = -79284909110828179L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;

}


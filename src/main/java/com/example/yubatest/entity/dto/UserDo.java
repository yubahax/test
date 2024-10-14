package com.example.yubatest.entity.dto;


import java.util.Date;
import java.io.Serializable;

import com.example.yubatest.entity.common.BaseDo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * user(User)实体类
 *
 * @author makejava
 * @since 2024-10-11 09:54:46
 */

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("User")
public class UserDo extends BaseDo {
    private static final long serialVersionUID = 615452172848310501L;

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


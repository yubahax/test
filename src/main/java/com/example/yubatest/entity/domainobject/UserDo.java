package com.example.yubatest.entity.domainobject;


import com.example.yubatest.entity.common.BaseDo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * user(User)实体类
 *
 * @author makejava
 * @since 2024-10-14 11:30:41
 */

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("User")
public class UserDo extends BaseDo {
    private static final long serialVersionUID = -27225637086570531L;

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


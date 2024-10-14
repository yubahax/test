package com.example.yubatest.entity.co;


import java.util.Date;
import java.io.Serializable;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

/**
 * user(User)实体类
 *
 * @author makejava
 * @since 2024-10-14 11:30:42
 */

@Data
public class UserCo implements Serializable {
    private static final long serialVersionUID = 678100104243171489L;

    @ApiModelProperty("主键")
    private Long id;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("年龄")
    private Integer age;

}


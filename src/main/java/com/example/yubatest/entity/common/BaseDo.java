package com.example.yubatest.entity.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class BaseDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(
            value = "id",
            type = IdType.ASSIGN_ID
    )
    protected Long id;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @JSONField(
            format = "yyyy-MM-dd HH:mm:ss"
    )
    @TableField(
            value = "create_time",
            fill = FieldFill.INSERT
    )
    protected LocalDateTime createTime;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @JSONField(
            format = "yyyy-MM-dd HH:mm:ss"
    )
    @TableField(
            value = "update_time",
            fill = FieldFill.INSERT_UPDATE
    )
    protected LocalDateTime updateTime;

    @TableField(
            value = "create_id",
            fill = FieldFill.INSERT
    )
    protected Long createId;
    @TableField(
            value = "update_id",
            fill = FieldFill.INSERT_UPDATE
    )
    protected Long updateId;
    @TableField(
            value = "env",
            fill = FieldFill.INSERT
    )
    protected String env = "PROD";
    @TableField(
            value = "delete_enum",
            fill = FieldFill.INSERT
    )
    @TableLogic(
            value = "FALSE",
            delval = "TRUE"
    )
    protected String deleteEnum = "FALSE";
    @TableField(
            value = "remarks",
            fill = FieldFill.INSERT
    )
    protected String remarks;
    @TableField(
            value = "create_name",
            fill = FieldFill.INSERT
    )
    protected String createName;
    @TableField(
            value = "update_name",
            fill = FieldFill.INSERT_UPDATE
    )
    protected String updateName;

    @Version
    @TableField(
            value = "version",
            fill = FieldFill.INSERT
    )
    protected Integer version;
}

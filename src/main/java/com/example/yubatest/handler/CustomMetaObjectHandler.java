package com.example.yubatest.handler;


import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static cn.dev33.satoken.SaManager.log;

@Component
@Primary
public class CustomMetaObjectHandler implements MetaObjectHandler {
    public CustomMetaObjectHandler() {
    }

    @Override
    public void insertFill(MetaObject metaObject) {

        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "deleteEnum", String.class, "FALSE");
        this.strictInsertFill(metaObject, "version", Integer.class, 1);
        this.strictInsertFill(metaObject,"createId", Long.class, Long.parseLong(StpUtil.getLoginId().toString()));
        this.strictInsertFill(metaObject,"env",String.class,"dev");
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject,"updateId", Long.class, Long.parseLong(StpUtil.getLoginId().toString()));
    }
}
package com.example.yubatest.Interceptor;

import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.lang.reflect.Field;
import java.sql.Connection;

/**
 * mybatis-plus 自定义拦截器
 * @author ml
 * @date 2023-05-15
 */

@Slf4j
public class MybatisInterceptor implements InnerInterceptor {

    @SneakyThrows
    @Override
    public void beforePrepare(StatementHandler sh, Connection connection, Integer transactionTimeout) {
        log.info("===========beforePrepare================");
        //实际执行的sql是经过层层封装，无法利用简单的一层反射获取到需要使用提供的快捷方法或者对获取到关键数据进行拼装
        MetaObject metaObject = MetaObject.forObject(sh, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,
                new DefaultReflectorFactory());

        // 先拦截到RoutingStatementHandler，里面有个StatementHandler类型的delegate变量，其实现类是BaseStatementHandler，然后就到BaseStatementHandler的成员变量mappedStatement
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");

        // id为执行的mapper方法的全路径名，如com.cq.UserMapper.insertUser， 便于后续使用反射
        String id = mappedStatement.getId();
        // sql语句类型 select、delete、insert、update
        String sqlCommandType = mappedStatement.getSqlCommandType().toString();
        // 数据库连接信息
        int index = id.lastIndexOf(".");
        Class<?> aClass = Class.forName(id.substring(0, index));

        String fullMethodName = id.substring(index + 1);
        String[] methodSplits = fullMethodName.split("_");
        String currentMethodName = methodSplits[0];

        BoundSql boundSql = sh.getBoundSql();
        // 获取到原始sql语句
        String sql = boundSql.getSql();
        log.info("SQL：{}", sql);


        // 增强sql
        // 通过反射，拦截方法上带有自定义@SqlPermission，并增强sql
        //离谱的是之前的反射无法生效，不知道为什么这个可以生效有待研究
        String mSql = sql.replace("demo", "kzz");
        // 直接增强sql
        //通过反射修改sql语句
        Field field = boundSql.getClass().getDeclaredField("sql");
        field.setAccessible(true);
        field.set(boundSql, mSql);
        log.info("增强后的SQL：{}", mSql); // 打印：增强后的SQL
        InnerInterceptor.super.beforePrepare(sh, connection, transactionTimeout);
    }
}

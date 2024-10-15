////
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by FernFlower decompiler)
////
//
//package com.jjb.saas.framework.datascope.datapermission.interceptor;
//
//import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
//import com.jjb.saas.framework.datascope.datapermission.handler.MDataPermissionHandler;
//import java.sql.Connection;
//import org.apache.ibatis.executor.statement.StatementHandler;
//import org.apache.ibatis.mapping.MappedStatement;
//import org.apache.ibatis.plugin.Interceptor;
//import org.apache.ibatis.plugin.Intercepts;
//import org.apache.ibatis.plugin.Invocation;
//import org.apache.ibatis.plugin.Signature;
//import org.apache.ibatis.reflection.MetaObject;
//import org.apache.ibatis.reflection.SystemMetaObject;
//
//@Intercepts({@Signature(
//        type = StatementHandler.class,
//        method = "prepare",
//        args = {Connection.class, Integer.class}
//)})
//public class MpDataScopeInterceptor implements Interceptor {
//    private MDataPermissionHandler dataPermissionHandler;
//    public static final String IGNORE_CLASS_METHOD = "com.jjb.saas.system.infrastructure.persistence.mapper.MenuMapper.listDataPermsValuesByUserIdIdAndMenuId";
//
//    public MpDataScopeInterceptor(MDataPermissionHandler dataPermissionHandler) {
//        this.dataPermissionHandler = dataPermissionHandler;
//    }
//
//    public Object intercept(Invocation invocation) throws Throwable {
//        StatementHandler statementHandler;
//        try {
//            statementHandler = (StatementHandler)PluginUtils.realTarget(invocation.getTarget());
//        } catch (Exception var7) {
//            return invocation.proceed();
//        }
//
//        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
//        MappedStatement mappedStatement = (MappedStatement)metaObject.getValue("delegate.mappedStatement");
//        String classNameAndMethod = mappedStatement.getId();
//        String sql = statementHandler.getBoundSql().getSql();
//        if (mappedStatement.getSqlCommandType().toString().equals("SELECT") && !"com.jjb.saas.system.infrastructure.persistence.mapper.MenuMapper.listDataPermsValuesByUserIdIdAndMenuId".equals(classNameAndMethod)) {
//            sql = this.dataPermissionHandler.getSqlsegment(sql, mappedStatement);
//        }
//
//        metaObject.setValue("delegate.boundSql.sql", sql);
//        return invocation.proceed();
//    }
//
//    public MpDataScopeInterceptor() {
//    }
//
//    public MDataPermissionHandler getDataPermissionHandler() {
//        return this.dataPermissionHandler;
//    }
//
//    public void setDataPermissionHandler(final MDataPermissionHandler dataPermissionHandler) {
//        this.dataPermissionHandler = dataPermissionHandler;
//    }
//
//    public boolean equals(final Object o) {
//        if (o == this) {
//            return true;
//        } else if (!(o instanceof MpDataScopeInterceptor)) {
//            return false;
//        } else {
//            MpDataScopeInterceptor other = (MpDataScopeInterceptor)o;
//            if (!other.canEqual(this)) {
//                return false;
//            } else {
//                Object this$dataPermissionHandler = this.getDataPermissionHandler();
//                Object other$dataPermissionHandler = other.getDataPermissionHandler();
//                if (this$dataPermissionHandler == null) {
//                    if (other$dataPermissionHandler != null) {
//                        return false;
//                    }
//                } else if (!this$dataPermissionHandler.equals(other$dataPermissionHandler)) {
//                    return false;
//                }
//
//                return true;
//            }
//        }
//    }
//
//    protected boolean canEqual(final Object other) {
//        return other instanceof MpDataScopeInterceptor;
//    }
//
//    public int hashCode() {
//        int PRIME = true;
//        int result = 1;
//        Object $dataPermissionHandler = this.getDataPermissionHandler();
//        result = result * 59 + ($dataPermissionHandler == null ? 43 : $dataPermissionHandler.hashCode());
//        return result;
//    }
//
//    public String toString() {
//        return "MpDataScopeInterceptor(super=" + super.toString() + ", dataPermissionHandler=" + this.getDataPermissionHandler() + ")";
//    }
//}

////
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by FernFlower decompiler)
////
//
//package com.jjb.saas.framework.datascope.datapermission.interceptor;
//
//import com.baomidou.mybatisplus.core.plugins.InterceptorIgnoreHelper;
//import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
//import com.baomidou.mybatisplus.extension.parser.JsqlParserSupport;
//import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
//import com.jjb.saas.framework.datascope.datapermission.handler.MDataPermissionHandler;
//import java.sql.SQLException;
//import java.util.Arrays;
//import org.apache.ibatis.executor.Executor;
//import org.apache.ibatis.mapping.BoundSql;
//import org.apache.ibatis.mapping.MappedStatement;
//import org.apache.ibatis.session.ResultHandler;
//import org.apache.ibatis.session.RowBounds;
//
//public class DataScopeInterceptor extends JsqlParserSupport implements InnerInterceptor {
//    private MDataPermissionHandler dataPermissionHandler;
//    public static final String[] IGNORE_CLASS_METHODS = new String[]{"com.jjb.saas.system.infrastructure.persistence.mapper.MenuMapper.listDataPermsValuesByUserIdIdAndMenuId", "com.jjb.saas.system.infrastructure.persistence.mapper.TenantMapper.listSelfAndChildrenTenantIdsByTenantId", "com.jjb.saas.auth.infrastructure.persistence.mapper.TerminalMapper.com.jjb.saas.auth.infrastructure.persistence.mapper.TerminalMapper"};
//
//    public DataScopeInterceptor(MDataPermissionHandler dataPermissionHandler) {
//        this.dataPermissionHandler = dataPermissionHandler;
//    }
//
//    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
//        if (!InterceptorIgnoreHelper.willIgnoreDataPermission(ms.getId())) {
//            String classNameAndMethod = ms.getId();
//            PluginUtils.MPBoundSql mpBoundSql = PluginUtils.mpBoundSql(boundSql);
//            String sql = mpBoundSql.sql();
//            if (!Arrays.asList(IGNORE_CLASS_METHODS).contains(classNameAndMethod)) {
//                sql = this.dataPermissionHandler.getSqlsegment(sql, ms);
//            }
//
//            mpBoundSql.sql(sql);
//        }
//    }
//
//    public DataScopeInterceptor() {
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
//    public String toString() {
//        return "DataScopeInterceptor(super=" + super.toString() + ", dataPermissionHandler=" + this.getDataPermissionHandler() + ")";
//    }
//
//    public boolean equals(final Object o) {
//        if (o == this) {
//            return true;
//        } else if (!(o instanceof DataScopeInterceptor)) {
//            return false;
//        } else {
//            DataScopeInterceptor other = (DataScopeInterceptor)o;
//            if (!other.canEqual(this)) {
//                return false;
//            } else if (!super.equals(o)) {
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
//        return other instanceof DataScopeInterceptor;
//    }
//
//    public int hashCode() {
//        int PRIME = true;
//        int result = super.hashCode();
//        Object $dataPermissionHandler = this.getDataPermissionHandler();
//        result = result * 59 + ($dataPermissionHandler == null ? 43 : $dataPermissionHandler.hashCode());
//        return result;
//    }
//}

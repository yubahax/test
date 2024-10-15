////
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by FernFlower decompiler)
////
//
//package com.jjb.saas.framework.datascope.datapermission.handler;
//
//import cn.hutool.core.util.ObjectUtil;
//import com.jjb.saas.framework.api.system.ListDataPermissionsByMenuKeyApi;
//import com.jjb.saas.framework.auth.utils.AuthContext;
//import com.jjb.saas.framework.datascope.annotation.DataScope;
//import com.jjb.saas.framework.datascope.annotation.DataScopes;
//import com.jjb.saas.framework.datascope.annotation.OpenEnv;
//import com.jjb.saas.framework.datascope.api.dto.DataPermsValueDTO;
//import com.jjb.saas.framework.datascope.enums.TenantDataScopeEnum;
//import com.jjb.saas.framework.utils.JacksonUtils;
//import com.jjb.saas.framework.utils.SqlUtil;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//import org.apache.ibatis.mapping.MappedStatement;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class MDataPermissionHandler {
//    private static final Logger log = LoggerFactory.getLogger(MDataPermissionHandler.class);
//    private static final String WHERE_CRON = "WHERE";
//    private static final String ORDER_CRON = "ORDER BY";
//    private static final String GROUP_CRON = "GROUP BY";
//    private static final String LIMIT_CRON = "LIMIT";
//    private static final String COUNT_TOTAL = ") TOTAL";
//    private ListDataPermissionsByMenuKeyApi listDataPermissionsByMenuKeyApi;
//    private String defaultTenantIds;
//    private String dataPermsDefaultVersion;
//
//    public MDataPermissionHandler(String defaultTenantIds, String dataPermsDefaultVersion, ListDataPermissionsByMenuKeyApi listDataPermissionsByMenuKeyApi) {
//        this.defaultTenantIds = defaultTenantIds;
//        this.listDataPermissionsByMenuKeyApi = listDataPermissionsByMenuKeyApi;
//        this.dataPermsDefaultVersion = dataPermsDefaultVersion;
//    }
//
//    public String getSqlsegment(String sql, MappedStatement ms) {
//        try {
//            String id = ms.getId();
//            int index = id.lastIndexOf(".");
//            Class<?> aClass = Class.forName(id.substring(0, index));
//            String fullMethodName = id.substring(index + 1);
//            String[] methodSplits = fullMethodName.split("_");
//            String currentMethodName = methodSplits[0];
//            OpenEnv openEnv = (OpenEnv)aClass.getAnnotation(OpenEnv.class);
//            if (ObjectUtil.isNotEmpty(openEnv)) {
//                sql = SqlUtil.getEvnConditionsSql(sql, AuthContext.getEnv());
//            }
//
//            DataScopes dataPermsScope = (DataScopes)aClass.getAnnotation(DataScopes.class);
//            if (ObjectUtil.isEmpty(dataPermsScope)) {
//                return sql;
//            } else {
//                DataScope[] values = dataPermsScope.value();
//                Optional<DataScope> first = Arrays.stream(values).filter((ex) -> {
//                    return ex.method().equals(currentMethodName);
//                }).findFirst();
//                if (!first.isPresent()) {
//                    return sql;
//                } else {
//                    sql = sql.replaceAll("\\s+", " ");
//                    sql = sql.replaceAll("order by", "ORDER BY");
//                    sql = sql.replaceAll("ORDER by", "ORDER BY");
//                    sql = sql.replaceAll("order BY", "ORDER BY");
//                    sql = sql.replaceAll("GROUP by", "GROUP BY");
//                    sql = sql.replaceAll("group by", "GROUP BY");
//                    sql = sql.replaceAll("group BY", "GROUP BY");
//                    sql = sql.replaceAll("limit", "LIMIT");
//                    DataScope dataScope = (DataScope)first.get();
//                    String countPreSql = "";
//                    String countSuffixSql = "";
//                    if (sql.contains(") TOTAL")) {
//                        countPreSql = sql.substring(0, sql.lastIndexOf(") TOTAL"));
//                        countSuffixSql = sql.substring(sql.lastIndexOf(") TOTAL"));
//                    }
//
//                    if (ObjectUtil.isNotEmpty(countPreSql)) {
//                        sql = countPreSql;
//                    }
//
//                    String preSql = "";
//                    String suffixSql = "";
//                    if (sql.contains("LIMIT") || sql.contains("LIMIT".toLowerCase())) {
//                        preSql = sql.substring(0, sql.lastIndexOf("LIMIT"));
//                        suffixSql = sql.substring(sql.lastIndexOf("LIMIT") - 1);
//                    }
//
//                    String orderQuerySql = "";
//                    String orderSuffixSql = "";
//                    if (ObjectUtil.isNotEmpty(preSql)) {
//                        if (!preSql.contains("ORDER BY") && !preSql.contains("ORDER BY".toLowerCase())) {
//                            orderQuerySql = preSql;
//                        } else {
//                            orderQuerySql = preSql.substring(0, preSql.lastIndexOf("ORDER BY"));
//                            orderSuffixSql = preSql.substring(preSql.lastIndexOf("ORDER BY") - 1);
//                        }
//                    } else if (!sql.contains("ORDER BY") && !sql.contains("ORDER BY".toLowerCase())) {
//                        orderQuerySql = sql;
//                    } else {
//                        orderQuerySql = sql.substring(0, sql.lastIndexOf("ORDER BY"));
//                        orderSuffixSql = sql.substring(sql.lastIndexOf("ORDER BY") - 1);
//                    }
//
//                    StringBuilder newSql = new StringBuilder();
//                    String querySql = "";
//                    String groupSql = "";
//                    if (ObjectUtil.isNotEmpty(orderQuerySql)) {
//                        if (!orderQuerySql.contains("GROUP BY") && !orderQuerySql.contains("GROUP BY".toLowerCase())) {
//                            if (!orderQuerySql.contains("WHERE") && !orderQuerySql.contains("WHERE".toLowerCase())) {
//                                orderQuerySql = orderQuerySql + " " + "WHERE" + " 1 = 1";
//                            }
//                        } else {
//                            querySql = orderQuerySql.substring(0, orderQuerySql.lastIndexOf("GROUP BY"));
//                            groupSql = orderQuerySql.substring(orderQuerySql.lastIndexOf("GROUP BY") - 1);
//                            if (!querySql.contains("WHERE") && !querySql.contains("WHERE".toLowerCase())) {
//                                querySql = querySql + " " + "WHERE" + " 1 = 1";
//                            }
//                        }
//
//                        if (ObjectUtil.isNotEmpty(querySql)) {
//                            newSql.append(querySql);
//                        } else {
//                            newSql.append(orderQuerySql);
//                        }
//                    } else {
//                        if (!sql.contains("GROUP BY") && !sql.contains("GROUP BY".toUpperCase())) {
//                            if (!sql.contains("WHERE") && !sql.contains("WHERE".toLowerCase())) {
//                                sql = sql + " " + "WHERE" + " 1 = 1";
//                            }
//                        } else {
//                            querySql = sql.substring(0, sql.lastIndexOf("GROUP BY"));
//                            groupSql = sql.substring(sql.lastIndexOf("GROUP BY") - 1);
//                            if (!querySql.contains("WHERE") && !querySql.contains("WHERE".toLowerCase())) {
//                                querySql = querySql + " " + "WHERE" + " 1 = 1";
//                            }
//                        }
//
//                        if (ObjectUtil.isNotEmpty(querySql)) {
//                            newSql.append(querySql);
//                        } else {
//                            newSql.append(sql);
//                        }
//                    }
//
//                    List objectList;
//                    if ("OLD".equals(this.dataPermsDefaultVersion)) {
//                        objectList = (List)Arrays.stream(this.defaultTenantIds.split(",")).map(Long::parseLong).collect(Collectors.toList());
//                        if (ObjectUtil.isNotEmpty(dataScope.tenantAlias()) && AuthContext.getTenantId() != null && !objectList.contains(AuthContext.getTenantId())) {
//                            newSql.append(" and ").append(dataScope.tenantAlias()).append(" = ").append(AuthContext.getTenantId());
//                        }
//                    } else if (ObjectUtil.isNotEmpty(dataScope.tenantAlias())) {
//                        Long tenantId = AuthContext.getTenantId();
//                        log.info("租户数据权当前authContext租户ID{}", tenantId);
//                        String tenantParentIds;
//                        String parentTenantIds;
//                        List tenantDistinctList;
//                        String wrraperValue;
//                        if (TenantDataScopeEnum.UP.equals(dataScope.type())) {
//                            tenantParentIds = AuthContext.getTenantParentIds();
//                            log.info("租户数据权当前authContext父级租户IDS{}", tenantParentIds);
//                            if (ObjectUtil.isNotEmpty(tenantId) && ObjectUtil.isEmpty(tenantParentIds)) {
//                                parentTenantIds = this.listDataPermissionsByMenuKeyApi.getTenantParentIdsById(tenantId);
//                                log.info("查询父级IDS得到结果：{}", parentTenantIds);
//                                if (ObjectUtil.isNotEmpty(parentTenantIds)) {
//                                    tenantParentIds = parentTenantIds;
//                                }
//                            }
//
//                            if (ObjectUtil.isNotEmpty(tenantParentIds)) {
//                                assert tenantParentIds != null;
//
//                                String[] splits = tenantParentIds.split(",");
//                                tenantDistinctList = (List)Arrays.stream(splits).map((ex) -> {
//                                    return "'" + ex + "'";
//                                }).collect(Collectors.toList());
//                                wrraperValue = String.join(",", tenantDistinctList);
//                                newSql.append(" and ").append(dataScope.tenantAlias()).append(" in ").append(" (").append(wrraperValue).append(") ");
//                            }
//                        } else if (TenantDataScopeEnum.DOWM.equals(dataScope.type())) {
//                            if (ObjectUtil.isNotEmpty(tenantId)) {
//                                List<Long> tenantIds = this.listDataPermissionsByMenuKeyApi.listSelfAndChildrenTenantIdsByTenantId(tenantId);
//                                log.info("租户数据权限获取租户及其以下ID：入参{},结果{}", tenantId, tenantIds);
//                                if (ObjectUtil.isNotEmpty(tenantIds)) {
//                                    List<String> permsValueList = (List)tenantIds.stream().map((ex) -> {
//                                        return "'" + ex + "'";
//                                    }).collect(Collectors.toList());
//                                    String wrraperValue = String.join(",", permsValueList);
//                                    newSql.append(" and ").append(dataScope.tenantAlias()).append(" in ").append(" (").append(wrraperValue).append(") ");
//                                }
//                            }
//                        } else if (TenantDataScopeEnum.CURRENT.equals(dataScope.type())) {
//                            if (ObjectUtil.isEmpty(tenantId)) {
//                                log.info("超管租户----------");
//                                newSql.append(" and ").append(dataScope.tenantAlias()).append(" IS NULL ");
//                            }
//
//                            if (ObjectUtil.isNotNull(tenantId)) {
//                                log.info("其他租户----------{}", tenantId);
//                                newSql.append(" and ").append(dataScope.tenantAlias()).append(" = ").append(tenantId).append(" ");
//                            }
//                        } else {
//                            tenantParentIds = AuthContext.getTenantParentIds();
//                            log.info("租户数据权当前authContext父级租户IDS{}", tenantParentIds);
//                            if (ObjectUtil.isNotEmpty(tenantId) && ObjectUtil.isEmpty(tenantParentIds)) {
//                                parentTenantIds = this.listDataPermissionsByMenuKeyApi.getTenantParentIdsById(tenantId);
//                                log.info("查询父级IDS得到结果：{}", parentTenantIds);
//                                if (ObjectUtil.isNotEmpty(parentTenantIds)) {
//                                    tenantParentIds = parentTenantIds;
//                                }
//                            }
//
//                            List<String> tenantIdList = new ArrayList();
//                            List permsValueList;
//                            if (ObjectUtil.isNotEmpty(tenantParentIds)) {
//                                assert tenantParentIds != null;
//
//                                String[] splits = tenantParentIds.split(",");
//                                permsValueList = (List)Arrays.stream(splits).map((ex) -> {
//                                    return "'" + ex + "'";
//                                }).collect(Collectors.toList());
//                                tenantIdList.addAll(permsValueList);
//                            }
//
//                            if (ObjectUtil.isNotEmpty(tenantId)) {
//                                tenantDistinctList = this.listDataPermissionsByMenuKeyApi.listSelfAndChildrenTenantIdsByTenantId(tenantId);
//                                log.info("租户数据权限获取租户及其以下ID：入参{},结果{}", tenantId, tenantDistinctList);
//                                if (ObjectUtil.isNotEmpty(tenantDistinctList)) {
//                                    permsValueList = (List)tenantDistinctList.stream().map((ex) -> {
//                                        return "'" + ex + "'";
//                                    }).collect(Collectors.toList());
//                                    tenantIdList.addAll(permsValueList);
//                                }
//                            }
//
//                            if (ObjectUtil.isNotEmpty(tenantIdList)) {
//                                tenantDistinctList = (List)tenantIdList.stream().distinct().collect(Collectors.toList());
//                                wrraperValue = String.join(",", tenantDistinctList);
//                                newSql.append(" and ").append(dataScope.tenantAlias()).append(" in ").append(" (").append(wrraperValue).append(") ");
//                            }
//                        }
//                    }
//
//                    objectList = this.listDataPermissionsByMenuKeyApi.listPermissions(dataScope.menuPerms());
//                    List<DataPermsValueDTO> dataPermsValueDTOS = new ArrayList();
//                    if (ObjectUtil.isNotEmpty(objectList)) {
//                        dataPermsValueDTOS = (List)objectList.stream().map((o) -> {
//                            return (DataPermsValueDTO)JacksonUtils.from(JacksonUtils.to(o), DataPermsValueDTO.class);
//                        }).collect(Collectors.toList());
//                    }
//
//                    if (ObjectUtil.isNotEmpty(dataPermsValueDTOS)) {
//                        StringBuilder conditionStr = new StringBuilder();
//                        Optional<DataPermsValueDTO> firstDTO = ((List)dataPermsValueDTOS).stream().filter((ex) -> {
//                            return ObjectUtil.isNotEmpty(ex.getDataPermsValue());
//                        }).findFirst();
//                        if (firstDTO.isPresent()) {
//                            DataPermsValueDTO dataPermsValueDTO = (DataPermsValueDTO)firstDTO.get();
//                            conditionStr.append(" and ");
//                            conditionStr.append("(");
//                            String[] splits = dataPermsValueDTO.getDataPermsValue().split(",");
//                            List<String> permsValues = (List)Arrays.stream(splits).map((ex) -> {
//                                return "'" + ex + "'";
//                            }).collect(Collectors.toList());
//                            String temp = String.join(",", permsValues);
//                            conditionStr.append(dataPermsValueDTO.getConditionValue()).append(" ( ").append(temp).append(" ) ");
//
//                            for(int i = 1; i < ((List)dataPermsValueDTOS).size(); ++i) {
//                                if (ObjectUtil.isNotEmpty(((DataPermsValueDTO)((List)dataPermsValueDTOS).get(i)).getDataPermsValue())) {
//                                    conditionStr.append(" or ");
//                                    String[] splitArrays = ((DataPermsValueDTO)((List)dataPermsValueDTOS).get(i)).getDataPermsValue().split(",");
//                                    List<String> permsValueList = (List)Arrays.stream(splitArrays).map((ex) -> {
//                                        return "'" + ex + "'";
//                                    }).collect(Collectors.toList());
//                                    String tempstr = String.join(",", permsValueList);
//                                    conditionStr.append(((DataPermsValueDTO)((List)dataPermsValueDTOS).get(i)).getConditionValue()).append(" ( ").append(tempstr).append(" ) ");
//                                }
//                            }
//
//                            conditionStr.append(") ");
//                        }
//
//                        newSql.append(conditionStr);
//                    }
//
//                    newSql.append(groupSql);
//                    newSql.append(orderSuffixSql);
//                    newSql.append(suffixSql);
//                    newSql.append(countSuffixSql);
//                    return newSql.toString();
//                }
//            }
//        } catch (ClassNotFoundException var35) {
//            ClassNotFoundException e = var35;
//            e.printStackTrace();
//            return sql;
//        }
//    }
//
//    public MDataPermissionHandler() {
//    }
//}

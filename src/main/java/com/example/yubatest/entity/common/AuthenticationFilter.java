////
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by FernFlower decompiler)
////
//
//package com.jjb.saas.framework.auth.filter;
//
//import cn.hutool.core.lang.TypeReference;
//import cn.hutool.core.util.IdUtil;
//import cn.hutool.core.util.NumberUtil;
//import cn.hutool.core.util.ObjectUtil;
//import cn.hutool.core.util.StrUtil;
//import cn.hutool.http.HttpRequest;
//import cn.hutool.http.HttpResponse;
//import cn.hutool.http.HttpUtil;
//import cn.hutool.http.Method;
//import cn.hutool.json.JSONUtil;
//import com.alibaba.cola.dto.SingleResponse;
//import com.alibaba.cola.exception.BizException;
//import com.jjb.saas.framework.auth.model.SSOUser;
//import com.jjb.saas.framework.auth.properties.DragonGatewayProperties;
//import com.jjb.saas.framework.auth.utils.AuthContext;
//import com.jjb.saas.framework.enums.enums.HttpUrlEnum;
//import com.jjb.saas.framework.redis.service.RedisService;
//import com.jjb.saas.framework.utils.JwtTokenUtils;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.slf4j.MDC;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//@Component
//public class AuthenticationFilter extends OncePerRequestFilter {
//    private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);
//    private final DragonGatewayProperties dragonGatewayProperties;
//    private final RedisService redisService;
//
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String token = request.getHeader("token");
//        if (StringUtils.isEmpty(token)) {
//            token = request.getParameter("token");
//        }
//
//        String tenantId = request.getHeader("tenant_id");
//        if (StringUtils.isEmpty(tenantId)) {
//            tenantId = request.getParameter("tenant_id");
//        }
//
//        if (!StringUtils.isEmpty(tenantId) && "null".equals(tenantId)) {
//            tenantId = null;
//        }
//
//        String rpcType = request.getHeader("rpcType");
//        if (StringUtils.isEmpty(rpcType)) {
//            rpcType = request.getParameter("rpcType");
//        }
//
//        if (!StringUtils.isEmpty(tenantId) && "null".equals(tenantId)) {
//            tenantId = null;
//        }
//
//        String tenantIdStr = request.getHeader("tenantId");
//        if (StringUtils.isEmpty(tenantIdStr)) {
//            tenantIdStr = request.getParameter("tenantId");
//        }
//
//        if (!StringUtils.isEmpty(tenantIdStr) && ("null".equals(tenantIdStr) || !NumberUtil.isLong(tenantIdStr))) {
//            tenantIdStr = null;
//        }
//
//        if (ObjectUtil.isNotEmpty(tenantIdStr)) {
//            log.info("==========================tenantIdStr={}", tenantIdStr);
//            tenantId = tenantIdStr;
//            token = null;
//        }
//
//        if (StringUtils.isEmpty(token)) {
//            token = request.getHeader("Magic-Token");
//            if (StringUtils.isEmpty(token)) {
//                token = request.getParameter("Magic-Token");
//            }
//
//            if (ObjectUtil.isEmpty(token)) {
//                token = request.getHeader("token");
//            }
//
//            if (ObjectUtil.isEmpty(token)) {
//                token = request.getParameter("token");
//            }
//        }
//
//        String orgId = request.getHeader("org_id");
//        if (StringUtils.isEmpty(orgId)) {
//            orgId = request.getParameter("org_id");
//        }
//
//        String appKey = request.getHeader("app_key");
//        if (StringUtils.isEmpty(appKey)) {
//            appKey = request.getParameter("app_key");
//        }
//
//        if (ObjectUtil.isEmpty(appKey)) {
//            appKey = request.getHeader("appKey");
//            if (ObjectUtil.isEmpty(appKey)) {
//                appKey = request.getParameter("appKey");
//            }
//        }
//
//        String clientId = request.getHeader("client_id");
//        log.info("2.2获取请求头终端id{}", clientId);
//        if (StringUtils.isEmpty(clientId)) {
//            clientId = request.getParameter("client_id");
//            log.info("2.3获取参数中的终端id{}", clientId);
//            if (ObjectUtil.isEmpty(clientId)) {
//                clientId = request.getHeader("clientId");
//            }
//
//            log.info("2.4最后获取的终端id{}", clientId);
//        }
//
//        String sid = request.getHeader("sid") == null ? IdUtil.objectId() : request.getHeader("sid");
//        SSOUser ssoUser = null;
//        String json;
//        if (StrUtil.isNotBlank(token) && token.startsWith("jjb-saas-auth:oauth:")) {
//            token = token.replaceAll("jjb-saas-auth:oauth:", "").trim();
//            json = null;
//
//            try {
//                json = JwtTokenUtils.parseToken(token);
//            } catch (Exception var34) {
//            }
//
//            if (null != json) {
//                ssoUser = (SSOUser)JSONUtil.toBean(json, SSOUser.class);
//            } else {
//                ssoUser = new SSOUser();
//            }
//
//            ssoUser.setToken("jjb-saas-auth:oauth:" + token);
//            ssoUser.setSid(sid);
//            if (StringUtils.isNotBlank(orgId)) {
//                try {
//                    ssoUser.setOrgId(Long.parseLong(orgId));
//                } catch (Exception var33) {
//                }
//            }
//
//            if (StringUtils.isNotBlank(appKey)) {
//                ssoUser.setAppKey(appKey);
//            }
//
//            if (ObjectUtil.isNotEmpty(clientId) && ObjectUtil.isEmpty(json) && "false".equals(this.dragonGatewayProperties.getIsSystem())) {
//                this.logger.info("获取端：" + clientId);
//
//                try {
//                    Map<String, Object> params = new HashMap();
//                    params.put("clientId", clientId);
//                    log.info("根据终端ID查询租户信息地址：{}", this.dragonGatewayProperties.getBaseurl() + HttpUrlEnum.TENANT_CLIENT_ID_URL.getValue());
//                    log.info("根据终端ID查询租户信息参数：{}", params);
//                    log.info("请确认 DragonGatewayProperties 的配置");
//                    HttpRequest tenantRequest = HttpUtil.createRequest(Method.GET, this.dragonGatewayProperties.getBaseurl() + HttpUrlEnum.TENANT_CLIENT_ID_URL.getValue());
//                    tenantRequest.setConnectionTimeout(5000);
//                    tenantRequest.form(params);
//                    HttpResponse execute = tenantRequest.execute();
//                    String body = execute.body();
//                    SingleResponse tenantResponse = (SingleResponse)JSONUtil.toBean(body, new TypeReference<SingleResponse>() {
//                    }, true);
//                    log.info("根据终端ID查询租户信息结果：{}", tenantResponse);
//                    if (tenantResponse.isSuccess()) {
//                        Map datas = (Map)tenantResponse.getData();
//                        this.logger.info("获取租户信息：" + datas);
//                        if (ObjectUtil.isNotEmpty(datas.get("id"))) {
//                            ssoUser.setTenantId(Long.parseLong((String)datas.get("id")));
//                        }
//
//                        if (ObjectUtil.isNotEmpty(datas.get("parentIds"))) {
//                            ssoUser.setTenantParentIds((String)datas.get("parentIds"));
//                        }
//                    }
//                } catch (Exception var32) {
//                    Exception e = var32;
//                    this.logger.error("获取租户ID错误", e);
//                    throw new BizException("终端未配置租户");
//                }
//            }
//        } else {
//            ssoUser = new SSOUser();
//            ssoUser.setClientId(clientId);
//            ssoUser.setTenantId(tenantId != null && !tenantId.equals("null") ? Long.parseLong(tenantId) : null);
//            String resUrl;
//            if (StrUtil.isNotEmpty(clientId)) {
//                this.logger.info("获取端：" + clientId);
//
//                try {
//                    Map<String, Object> params = new HashMap();
//                    params.put("clientId", clientId);
//                    log.info("根据终端ID查询租户信息地址：{}", this.dragonGatewayProperties.getBaseurl() + HttpUrlEnum.TENANT_CLIENT_ID_URL.getValue());
//                    log.info("根据终端ID查询租户信息参数：{}", params);
//                    log.info("请确认 DragonGatewayProperties 的配置");
//                    HttpRequest tenantRequest = HttpUtil.createRequest(Method.GET, this.dragonGatewayProperties.getBaseurl() + HttpUrlEnum.TENANT_CLIENT_ID_URL.getValue());
//                    tenantRequest.setConnectionTimeout(5000);
//                    tenantRequest.form(params);
//                    HttpResponse execute = tenantRequest.execute();
//                    resUrl = execute.body();
//                    SingleResponse tenantResponse = (SingleResponse)JSONUtil.toBean(resUrl, new TypeReference<SingleResponse>() {
//                    }, true);
//                    log.info("根据终端ID查询租户信息结果：{}", tenantResponse);
//                    if (tenantResponse.isSuccess()) {
//                        Map datas = (Map)tenantResponse.getData();
//                        this.logger.info("获取租户信息：" + datas);
//                        if (ObjectUtil.isNotEmpty(datas.get("id"))) {
//                            ssoUser.setTenantId(Long.parseLong((String)datas.get("id")));
//                        }
//
//                        if (ObjectUtil.isNotEmpty(datas.get("parentIds"))) {
//                            ssoUser.setTenantParentIds((String)datas.get("parentIds"));
//                        }
//                    }
//                } catch (Exception var31) {
//                    Exception e = var31;
//                    this.logger.error("获取租户ID错误", e);
//                    throw new BizException("终端未配置租户");
//                }
//            }
//
//            json = request.getHeader("appKeyType");
//            if (ObjectUtil.isNotEmpty(json) && json.equals("app") && StrUtil.isNotEmpty(appKey) && ObjectUtil.isNotEmpty(request.getHeader("app_key")) && ObjectUtil.isNotEmpty(tenantId) && !"null".equals(tenantId)) {
//                log.info("=============================================开始获取平台相关信息=============");
//                Map datas = null;
//                Object platformInfo = this.redisService.get(tenantId + "_" + appKey);
//                if (ObjectUtil.isNotEmpty(platformInfo)) {
//                    log.info("=============================================缓存获取平台信息============={}", platformInfo);
//                    datas = (Map)JSONUtil.toBean(platformInfo.toString(), Map.class);
//                } else {
//                    log.info("=============================================HTTP调用资源中心获取平台信息=============");
//                    if (ObjectUtil.isNotEmpty(this.dragonGatewayProperties.getRes()) && !"false".equals(this.dragonGatewayProperties.getRes())) {
//                        resUrl = this.dragonGatewayProperties.getRes() + HttpUrlEnum.GET_RES_PLATFORM.getValue();
//                        log.info("开始获取平台信息：{}", resUrl);
//                        HttpRequest httpResRequest = HttpUtil.createGet(resUrl);
//                        Map<String, Object> resParam = new HashMap();
//                        resParam.put("tenantId", Long.parseLong(tenantId));
//                        resParam.put("appKey", appKey);
//                        httpResRequest.form(resParam);
//                        SingleResponse resResponseObj = null;
//
//                        try {
//                            log.info("获取平台信息参数：{}", resParam);
//                            HttpResponse resResponse = httpResRequest.setReadTimeout(2000).execute();
//                            String bodyRes = resResponse.body();
//                            resResponseObj = (SingleResponse)JSONUtil.toBean(bodyRes, new TypeReference<SingleResponse>() {
//                            }, true);
//                            log.info("获取平台信息结果：{}", resResponseObj);
//                        } catch (Exception var30) {
//                            Exception e = var30;
//                            log.error("获取平台信息异常：{}", e);
//                        }
//
//                        if (ObjectUtil.isNotEmpty(resResponseObj) && resResponseObj.isSuccess()) {
//                            if (ObjectUtil.isNotEmpty(resResponseObj.getData())) {
//                                datas = (Map)JSONUtil.toBean(resResponseObj.getData().toString(), Map.class);
//                            }
//
//                            this.logger.info("获取平台信息结果data：" + datas);
//                        }
//                    }
//
//                    log.info("=============================================获取对应的平台ID跟平台名称=============");
//                    if (ObjectUtil.isNotEmpty(datas)) {
//                        log.info("=============================================平台信息============={}", datas);
//                        if (ObjectUtil.isNotEmpty(datas.get("id"))) {
//                            ssoUser.setPlatformId(Long.parseLong(datas.get("id").toString()));
//                        }
//
//                        if (ObjectUtil.isNotEmpty(datas.get("platName"))) {
//                            ssoUser.setPlatformName(datas.get("platName").toString());
//                        }
//                    }
//                }
//            }
//
//            ssoUser.setSid(sid);
//            ssoUser.setOrgId(orgId != null && !orgId.equals("null") ? Long.parseLong(orgId) : null);
//            ssoUser.setAppKey(appKey);
//            ssoUser.setRpcTypeEnum(rpcType != null && !rpcType.equals("null") ? rpcType : null);
//        }
//
//        MDC.put("sid", sid);
//        request.setAttribute("loginVal_attribute", ssoUser);
//        AuthContext.set(ssoUser);
//
//        try {
//            filterChain.doFilter(request, response);
//        } finally {
//            AuthContext.remove();
//        }
//
//    }
//
//    public AuthenticationFilter(final DragonGatewayProperties dragonGatewayProperties, final RedisService redisService) {
//        this.dragonGatewayProperties = dragonGatewayProperties;
//        this.redisService = redisService;
//    }
//}

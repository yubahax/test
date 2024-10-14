package com.example.yubatest.aop;

import cn.soboys.restapispringbootstarter.annotation.EnableRestFullApi;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 创建自定义注解
@Target(ElementType.TYPE) // 目标是类级别
@Retention(RetentionPolicy.RUNTIME) // 注解在运行时生效
@EnableDiscoveryClient // 开启服务发现客户端
@EnableRestFullApi // 自定义的REST API注解
@SpringBootApplication // Spring Boot应用入口注解
public @interface TestApp {
}
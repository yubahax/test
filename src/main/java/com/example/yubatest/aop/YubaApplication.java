package com.example.yubatest.aop;

import cn.soboys.restapispringbootstarter.annotation.EnableRestFullApi;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.core.annotation.AliasFor;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.lang.annotation.*;

@Configuration(
        proxyBeanMethods = true
)
@EnableMBeanExport(
        registration = RegistrationPolicy.IGNORE_EXISTING
)
@EnableAspectJAutoProxy
@EnableDiscoveryClient
@EnableScheduling
@EnableAsync
@EnableCaching
@MapperScan
@ComponentScan
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableRestFullApi
@SpringBootApplication
@EnableSwagger2
public @interface YubaApplication {
    @AliasFor(
            annotation = ComponentScan.class,
            attribute = "basePackages"
    )
    String[] scanBasePackages() default {"com.example"};

//    @AliasFor(
//            annotation = MapperScan.class,
//            attribute = "basePackages"
//    )
//    String[] mapperPackages() default {"com.example.*.*.mapper"};
}

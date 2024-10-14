package com.example.yubatest;

import com.example.yubatest.aop.YubaApplication;
import org.springframework.boot.SpringApplication;

//@TestApp
@YubaApplication
//@EnableDiscoveryClient
//@EnableRestFullApi
//@SpringBootApplication
public class YubaTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(YubaTestApplication.class, args);
    }

}

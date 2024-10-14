package com.example.yubatest.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.soboys.restapispringbootstarter.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "【V1.0.0】测试1")
public class TestController {

    @GetMapping("/test")
    public Result test() {
        return Result.buildSuccess();
    }

    @PostMapping("/login")
    public Result login() {
        Long userId = 114514L;
        StpUtil.login(userId);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return Result.buildSuccess(tokenInfo.tokenValue);
    }


}

package com.plan.time.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.plan.time.request.user.UserLoginDTO;
import com.plan.time.result.R;
import com.plan.time.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api(tags = "用户登录认证")
@AllArgsConstructor
public class UserController {

    private UserInfoService userInfoService;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public R<String> login(@RequestBody UserLoginDTO userLoginDTO) {
        return userInfoService.login(userLoginDTO);
    }
}

package com.plan.time.controller;

import com.plan.time.response.captcha.CaptchaVO;
import com.plan.time.result.R;
import com.plan.time.service.CaptchaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pub")
@Api(tags = "图形验证码")
@AllArgsConstructor
public class CaptchaController {

    private CaptchaService captchaService;

    @GetMapping("/captcha")
    @ApiOperation("生成图形验证码")
    public R<CaptchaVO> generate() {
        return R.success(captchaService.generate());
    }

}

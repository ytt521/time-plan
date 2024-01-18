package com.plan.time.service.impl;

import cn.hutool.core.util.IdUtil;
import com.plan.time.constants.RedisConstants;
import com.plan.time.response.captcha.CaptchaVO;
import com.plan.time.service.CaptchaService;
import com.plan.time.utils.RedisUtil;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CaptchaServiceImpl implements CaptchaService {

    private RedisUtil redisUtil;

    @Override
    public CaptchaVO generate() {
        Captcha captcha = new SpecCaptcha(135, 35, 5);
        String uid = IdUtil.fastSimpleUUID();
        String key = RedisConstants.CAPTCHA_KEY + uid;
        redisUtil.set(key, captcha.text(),RedisConstants.CAPTCHA_EXPIRED_MINUTE);
        return CaptchaVO.builder().uid(uid).img(captcha.toBase64()).build();
    }
}

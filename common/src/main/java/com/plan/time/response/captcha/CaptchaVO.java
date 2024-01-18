package com.plan.time.response.captcha;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CaptchaVO {
    private String uid;
    private String img;
}
